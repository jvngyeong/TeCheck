package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DDL.command.SupplyCommand;
import DDL.service.goods.GoodsListService;
import DDL.service.supply.SupplyDeleteService;
import DDL.service.supply.SupplyDetailService;
import DDL.service.supply.SupplyListService;
import DDL.service.supply.SupplyUpdateService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("supply")
public class SupplyContoller {
	@Autowired
	GoodsListService goodsListService;

	@Autowired
	SupplyWriteService supplyWriteService;
	
	@Autowired
	SupplyListService supplyListService;
	
	@Autowired
	SupplyDetailService supplyDetailService;
	
	@Autowired
	SupplyUpdateService supplyUpdateService;
	
	@Autowired
	SupplyDeleteService supplyDeleteService;
	
	@GetMapping("supplyList")
	public String supplyList(Model model) {
		supplyListService.execute(model);
		return "thymeleaf/supply/supplyList";
	}
	
	@GetMapping("supplyWrite")
	public String supplyWrite(Model model) {
		SupplyCommand supplyCommand = new SupplyCommand();
		model.addAttribute("supplyCommand", supplyCommand);
		return "thymeleaf/supply/supplyWrite";
	}
	
	@PostMapping("supplyWrite")
	public String supplyWrite(@Validated SupplyCommand supplyCommand, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/supply/supplyWrite";
		}
		supplyWriteService.execute(supplyCommand);
		return "redirect:/supply/supplyList";
	}
	
	@GetMapping("goodsSelect")
	public String goodsSelect(@RequestParam(value="searchWord" , required = false) String searchWord
			, @RequestParam(value = "page" , required = false , defaultValue = "1") int page
			, Model model, HttpSession session) {
		goodsListService.execute(null, model, -1, session);
		return "thymeleaf/supply/goodsSelect";
	}
	
	@GetMapping("supplyDetail")
	public String supplyDetail(String supNum, Model model) {
		supplyDetailService.execute(supNum, model);
		return "thymeleaf/supply/supplyDetail";
	}
	
	@GetMapping("supplyUpdate")
	public String supplyUpdate(String supNum, Model model) {
		supplyDetailService.execute(supNum, model);
		return "thymeleaf/supply/supplyUpdate";
	}
	
	@PostMapping("supplyUpdate")
	public String supplyUpdate(@Validated SupplyCommand supplyCommand, BindingResult result, Model model) {
		if(result.hasErrors()) {
			supplyCommand.setContractPeriod(supplyCommand.getContractStart() + " - " + supplyCommand.getContractEnd());
			model.addAttribute("supplyCommand", supplyCommand);
			return "thymeleaf/supply/supplyUpdate";
		}
		supplyUpdateService.execute(supplyCommand);
		return "redirect:/supply/supplyList";
	}
	
	@GetMapping("supplyDelete")
	public String supplyDelete(String supNum) {
		supplyDeleteService.execute(supNum);
		return "redirect:/supply/supplyList";
	}
}
