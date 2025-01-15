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
import DDL.service.goodsOrder.GoodsOrderDeleteService;
import DDL.service.goodsOrder.GoodsOrderDetailService;
import DDL.service.goodsOrder.GoodsOrderListService;
import DDL.service.goodsOrder.GoodsOrderService;
import DDL.service.goodsOrder.GoodsOrderUpdateService;
import DDL.service.supply.SupplyListService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("goodsOrder")
public class GoodsOrderController {
	@Autowired
	SupplyListService supplyListService;
	
	@Autowired
	GoodsOrderService goodsOrderService;
	
	@Autowired
	GoodsOrderListService goodsOrderListService;
	
	@Autowired
	GoodsOrderDetailService goodsOrderDetailService;
	
	@Autowired
	GoodsOrderUpdateService goodsOrderUpdateService;
	
	@Autowired
	GoodsOrderDeleteService goodsOrderDeleteService;
	
	@GetMapping("goodsOrderList")
	public String goodsOrderList(Model model) {
		goodsOrderListService.execute(model);
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
	
	@GetMapping("goodsOrderUpdate")
	public String goodsOrderUpdate(String goodsOrderNum, Model model) {
		goodsOrderDetailService.execute(goodsOrderNum, model);
		return "thymeleaf/goodsOrder/goodsOrderUpdate";
	}
	
	@PostMapping("goodsOrderUpdate")
	public String goodsOrderUpdate(@Validated GoodsOrderCommand goodsOrderCommand, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("goodsOrderCommand", goodsOrderCommand);
			return "thymeleaf/goodsOrder/goodsOrderUpdate";
		}
		goodsOrderUpdateService.execute(goodsOrderCommand, session);
		return "redirect:/goodsOrder/goodsOrderList";
	}
	
	@GetMapping("goodsOrderDelete")
	public String goodsOrderDelete(String goodsOrderNum) {
		goodsOrderDeleteService.execute(goodsOrderNum);
		return "redirect:/goodsOrder/goodsOrderList";
	}
}
