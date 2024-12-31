package DDL.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("delivery")
public class DeliveryContoller {
	@GetMapping("deliveryPopup")
	public String deliveryPopup(String orderNum, Model model) {
		return "thymeleaf/delivery/deliveryPopup";
	}
}