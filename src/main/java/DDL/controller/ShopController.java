package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.domain.AuthInfoDTO;
import DDL.mapper.MemberMapper;
import DDL.service.goods.GoodsDetailService;
import DDL.service.goods.GoodsListService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("shop")
public class ShopController {
	@Autowired
	GoodsListService goodsListService;
	
	@Autowired
	GoodsDetailService goodsDetailService;
	
	@Autowired
	MemberMapper memberMapper;
	
	@GetMapping("shopList")
	public String shopList(Model model) {
		goodsListService.execute(model);
		return "thymeleaf/shop/shopList";
	}
	
	@GetMapping("shopDetail")
	public String shopDetail(String goodsNum, Model model, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		if(auth != null) {
			String memberId = auth.getUserId();
			String memberNum = memberMapper.getMemberNum(memberId);
			if(memberNum != null) {
				model.addAttribute("memberNum", memberNum);
			}
			else {
				model.addAttribute("memberNum", "emp");
			}
		}
		goodsDetailService.execute(goodsNum, model);
		return "thymeleaf/shop/shopDetail";
	}
}
