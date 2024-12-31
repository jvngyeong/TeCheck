package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.service.order.OrderListService;

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	OrderListService orderListService;
	@GetMapping("orderList")
	public String orderList(String memberNum, Model model) {
		orderListService.execute(memberNum, model);
		return "thymeleaf/order/orderList";
	}
	
	@GetMapping("empOrderList")
	public String orderList(Model model) {
		orderListService.execute(null, model);
		return "thymeleaf/order/empOrderList";
	}
}