package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String List(Model model) {
		goodsListService.execute(model);
		return "thymeleaf/goods/goodsList";
	}

	
	@GetMapping("goodsWrite")
	public String goodsWrite() {
		return "thymeleaf/goods/goodsWrite";
	}
	
	@PostMapping("goodsWrite")
	public String goodsWrite(Model model, GoodsCommand goodsCommand) {
		goodsWriteService.execute(goodsCommand);
		return "redirect:/goods/goodsList";
	}
	
	@GetMapping("goodsDetail")
	public String goodsDetail(String goodsNum, Model model) {
		goodsDetailService.execute(goodsNum, model);
		return "thymeleaf/goods/goodsDetail";
	}
	@GetMapping("goodsModify")
	public String goodsModify(String goodsNum, Model model) {
		goodsDetailService.execute(goodsNum, model);
		return "thymeleaf/goods/goodsModify";
	}
	@PostMapping("goodsModify")
	public String goodsModify(GoodsCommand goodsCommand) {
		goodsUpdateService.execute(goodsCommand);
		return "redirect:/goods/goodsDetail?goodsNum="+goodsCommand.getGoodsNum();
	}
	@GetMapping("goodsDelete")
	public String goodsDelete(String goodsNum) {
		goodsDeleteService.execute(goodsNum);
		return "redirect:/goods/goodsList";
	}
}
