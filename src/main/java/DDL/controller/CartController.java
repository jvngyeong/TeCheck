package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import DDL.service.cart.CartDeleteService;
import DDL.service.cart.CartListService;
import DDL.service.cart.CartMergeService;
import DDL.service.cart.CartUpdateService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("cart")
public class CartController {
	@Autowired
	CartMergeService cartMergeService;
	
	@Autowired
	CartListService cartListService;
	
	@Autowired
	CartDeleteService cartDeleteService;
	
	@Autowired
	CartUpdateService cartUpdateService;
	
	@GetMapping("cart")
	public String cart(Model model, HttpSession session) {
		cartListService.execute(model, session);
		return "thymeleaf/cart/cart";
	}
	
	@GetMapping("cartInsert")
	public @ResponseBody String cartInsert(String goodsNum, String memberNum, String cartQty) {
		if(memberNum != null && !memberNum.equals("")) {
			cartMergeService.execute(goodsNum, memberNum, cartQty);
			return "200";
		}
		else {
			return "000";
		}
	}
	
	@GetMapping("cartDelete")
	public @ResponseBody String cartDelete(String goodsNum, String memberNum) {
		return cartDeleteService.execute(goodsNum, memberNum);
	}
	
	@GetMapping("cartUpdate")
	public @ResponseBody void cartUpdate(String cartQty, String goodsNum, String memberNum) {
		cartUpdateService.execute(cartQty, goodsNum, memberNum);
	}
}
