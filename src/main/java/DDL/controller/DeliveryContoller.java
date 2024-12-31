package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.DeliveryCommand;
import DDL.service.delivery.DeliveryDeleteService;
import DDL.service.delivery.DeliveryStatusUpdateService;
import DDL.service.delivery.DeliveryWriteService;
import DDL.service.order.OrderDetailService;

@Controller
@RequestMapping("delivery")
public class DeliveryContoller {
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	DeliveryWriteService deliveryWriteService;
	
	@Autowired
	DeliveryDeleteService deliveryDeleteService;
	
	@Autowired
	DeliveryStatusUpdateService deliveryStatusUpdateService;

	@GetMapping("deliveryPopup")
	public String deliveryPopup(String orderNum, Model model) {
		orderDetailService.execute(orderNum, model);
		return "thymeleaf/delivery/deliveryPopup";
	}
	
	@PostMapping("deliveryWrite")
	public String deliveryWrite(DeliveryCommand deliveryCommand) {
		deliveryWriteService.execute(deliveryCommand);
		return "redirect:deliveryPopup?orderNum="+deliveryCommand.getOrderNum();
	}
	
	@PostMapping("deliveryDelete")
	public String deliveryDelete(DeliveryCommand deliveryCommand) {
		deliveryDeleteService.execute(deliveryCommand);
		return "redirect:deliveryPopup?orderNum="+deliveryCommand.getOrderNum();
	}
	
	@GetMapping("deliveryStatusUpdate")
	public String deliveryStatusUpdate(String orderNum) {
		deliveryStatusUpdateService.execute(orderNum);
		return "redirect:/order/empOrderList";
	}
}