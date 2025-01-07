package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DDL.domain.AuthInfoDTO;
import DDL.mapper.GoodsMapper;
import DDL.mapper.MemberMapper;
import DDL.service.goods.CategoryService;
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
	
	@Autowired
	CategoryService categoryService;
	@GetMapping("shopList")
	public String shopList(@RequestParam(value="searchWord" , required = false) String searchWord
			, Model model) {
		categoryService.execute(model);
		goodsListService.execute(searchWord, model);
		return "thymeleaf/shop/shopList";
	}
	@Autowired
	GoodsMapper goodsMapper;
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
		goodsMapper.goodsVisitCountUpdate(goodsNum);
		goodsDetailService.execute(goodsNum, model);
		
		return "thymeleaf/shop/shopDetail";
	}
}
