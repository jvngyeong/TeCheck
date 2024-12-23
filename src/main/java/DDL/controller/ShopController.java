package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.service.goods.GoodsDetailService;
import DDL.service.goods.GoodsListService;

@Controller
@RequestMapping("shop")
public class ShopController {
	@Autowired
	GoodsListService goodsListService;
	
	@Autowired
	GoodsDetailService goodsDetailService;
	@GetMapping("shopList")
	public String shopList(Model model) {
		goodsListService.execute(model);
		return "thymeleaf/shop/shopList";
	}
	
	@GetMapping("shopDetail")
	public String shopDetail(String goodsNum, Model model) {
		goodsDetailService.execute(goodsNum, model);
		return "thymeleaf/shop/shopDetail";
	}
}
