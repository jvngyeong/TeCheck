package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import DDL.service.stock.StockDataService;

@Controller
@RequestMapping("stock")
public class StockController {
	@Autowired
	StockDataService stockDataService;
	
	@GetMapping("stock")
	public String stock(Model model) {
		stockDataService.execute(model);
		return "thymeleaf/stock/stock";
	}
	
	@GetMapping("getStockData")
	public @ResponseBody String getStockData() {
		return "";
	}
}
