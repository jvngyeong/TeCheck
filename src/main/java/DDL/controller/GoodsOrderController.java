package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.GoodsOrderCommand;
import DDL.service.goodsOrder.GoodsOrderService;
import DDL.service.supply.SupplyListService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("goodsOrder")
public class GoodsOrderController {
	@Autowired
	SupplyListService supplyListService;
	
	@Autowired
	GoodsOrderService goodsOrderService;
	
	@GetMapping("goodsOrderList")
	public String goodsOrderList() {
		return "thymeleaf/goodsOrder/goodsOrderList";
	}
	
	@GetMapping("goodsOrder")
	public String goodsOrder(Model model) {
		GoodsOrderCommand goodsOrderCommand = new GoodsOrderCommand();	
		model.addAttribute("goodsOrderCommand", goodsOrderCommand);
		return "thymeleaf/goodsOrder/goodsOrder";
	}
	
	@GetMapping("supSearch")
	public String supSearch(Model model) {
		supplyListService.execute(model);
		return "thymeleaf/goodsOrder/supSearch";
	}
	
	@PostMapping("goodsOrder")
	public String goodsOrder(@Validated GoodsOrderCommand goodsOrderCommand, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			System.out.println(result.getFieldError());
			model.addAttribute("goodsOrderCommand", goodsOrderCommand);
			return "thymeleaf/goodsOrder/goodsOrder";
		}
		goodsOrderService.execute(goodsOrderCommand, session);
		return "redirect:/goodsOrder/goodsOrderList";
	}
}
