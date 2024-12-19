package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.GoodsCommand;
import DDL.service.goods.GoodsDeleteService;
import DDL.service.goods.GoodsDetailService;
import DDL.service.goods.GoodsListService;
import DDL.service.goods.GoodsUpdateService;
import DDL.service.goods.GoodsWriteService;

@Controller
@RequestMapping("goods")
public class GoodsController {
	@Autowired
    GoodsListService goodsListService;
    
    @Autowired
    GoodsWriteService goodsWriteService;
    
    @Autowired
    GoodsDetailService goodsDetailService;
    
    @Autowired
    GoodsUpdateService goodsUpdateService;
    
    @Autowired
    GoodsDeleteService goodsDeleteService;
	@GetMapping("goodsList")
	public String List() {
		return "thymeleaf/goods/goodsList";
	}
	@GetMapping("goodsForm")
	public String form() {
		return "thymeleaf/goods/goodsWrite";
	}
	@GetMapping("goodsWrite")
	public String goodsForm(Model model) {
		GoodsCommand goodsCommand = new GoodsCommand();
		model.addAttribute("goodsCommand", goodsCommand);
		return "thymeleaf/goods/goodsForm";
	}
	
	@GetMapping("goodsInfo")
	public String Info() {
		return "thymeleaf/goods/goodsInfo";
	}
	
	
}
