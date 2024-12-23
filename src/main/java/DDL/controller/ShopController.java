package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.service.goods.GoodsListService;

@Controller
@RequestMapping("shop")
public class ShopController {
	@Autowired
	GoodsListService goodsListService;
	@GetMapping("shopList")
	public String shopList(Model model) {
		goodsListService.execute(model);
		return "thymeleaf/shop/shopList";
	}
}
