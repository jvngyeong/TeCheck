package DDL.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import DDL.command.OrderCommand;
import DDL.domain.CartDTO;
import DDL.domain.CouponDTO;
import DDL.domain.GoodsCartDTO;
import DDL.domain.GoodsDTO;
import DDL.mapper.CouponMapper;
import DDL.mapper.GoodsMapper;
import DDL.service.IniPayReqService;
import DDL.service.cart.CartListService;
import DDL.service.order.OrderService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
	@Autowired
	CartListService cartListService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	IniPayReqService iniPayReqService;
	
	@Autowired
	GoodsMapper goodsMapper;
	
	@Autowired
	CouponMapper couponMapper;
	
	@GetMapping("purchaseList")
	public String purchaseList(Model model, HttpSession session, String goodsNum, String memberNum, String cartQty, String goodsPrice, String goodsName, String couponNum) {
		if(goodsNum == null) {
			cartListService.execute(model, session);
		}
		else {
			List<GoodsCartDTO> goodsCartDTOList = new ArrayList<GoodsCartDTO>();
			GoodsDTO goodsDTO = goodsMapper.goodsSelectOne(goodsNum);
			CartDTO cartDTO = new CartDTO();
			GoodsCartDTO goodsCartDTO = new GoodsCartDTO();
			goodsCartDTO.setCartDTO(cartDTO);
			goodsCartDTO.setGoodsDTO(goodsDTO);
			goodsCartDTO.getCartDTO().setMemberNum(memberNum);
			goodsCartDTO.getCartDTO().setCartQty(cartQty);
			goodsCartDTOList.add(goodsCartDTO);
			model.addAttribute("goodsCartDTOList", goodsCartDTOList);
			model.addAttribute("totalPrice", goodsPrice);
		}
		if(couponNum != null) {
			if(!couponNum.isEmpty()) {
				CouponDTO couponDTO = couponMapper.couponSelectOne(couponNum);
				model.addAttribute("couponDTO", couponDTO);
			}
		}
		return "thymeleaf/purchase/purchaseList";
	}
	
	@GetMapping("directPurchase")
	public @ResponseBody String directPurchase(String goodsNum, String memberNum, String cartQty, String goodsPrice, Model model) {
		if(memberNum == null) {
			return "000";
		}
		else {
			if(memberNum == "") {
				return "900";
			}
			else {
				List<GoodsCartDTO> goodsCartDTOList = new ArrayList<GoodsCartDTO>();
				GoodsDTO goodsDTO = goodsMapper.goodsSelectOne(goodsNum);
				CartDTO cartDTO = new CartDTO();
				GoodsCartDTO goodsCartDTO = new GoodsCartDTO();
				goodsCartDTO.setCartDTO(cartDTO);
				goodsCartDTO.setGoodsDTO(goodsDTO);
				goodsCartDTO.getCartDTO().setMemberNum(memberNum);
				goodsCartDTO.getCartDTO().setCartQty(cartQty);
				goodsCartDTOList.add(goodsCartDTO);
				model.addAttribute("goodsCartDTOList", goodsCartDTOList);
				model.addAttribute("totalPrice", goodsPrice);
				return "200";
			}
		}
	}
	
	@PostMapping("order")
	public String order(OrderCommand orderCommand, String[] cartQties, HttpSession session, Model model) {
		String orderNum = orderService.execute(orderCommand, cartQties, session, model);
		if(orderCommand.getOrderPrice() == 0) {
			System.out.println("성공");
		}
		return "redirect:paymentOk?orderNum="+orderNum;
	}
	
	@GetMapping("paymentOk")
	public String paymentOk(String orderNum, String goodsName, Model model, HttpSession session) {
		try {
			iniPayReqService.execute(orderNum, goodsName, model, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "thymeleaf/purchase/payment";
	}
}
