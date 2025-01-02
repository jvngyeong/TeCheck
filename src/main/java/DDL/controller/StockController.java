package DDL.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("stock")
public class StockController {
	@GetMapping("stock")
	public String stock() {
		return "thymeleaf/stock/stock";
	}
}
