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

import DDL.command.CheckCommand;
import DDL.mapper.AutoNumMapper;
import DDL.service.check.CheckDeleteService;
import DDL.service.check.CheckInsertService;
import DDL.service.check.CheckListService;
import DDL.service.check.CheckSelectService;
import DDL.service.check.CheckUpdateService;
import DDL.service.goods.GoodsListService;
import DDL.service.store.StoreListService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("check")
public class CheckController {
	@Autowired
	CheckInsertService checkInsertService;
	
	@Autowired
	CheckListService checkListService;
	
	@Autowired
	CheckSelectService checkSelectService;
	
	@Autowired
	CheckUpdateService checkUpdateService;
	
	@Autowired
	CheckDeleteService checkDeleteService;
	
	@GetMapping("checkList")
	public String checkList(Model model) {
		checkListService.execute(model);
		return "thymeleaf/check/checkList";
	}
	@Autowired
	AutoNumMapper autoNumMapper;
	@GetMapping("checkWrite")
	public String checkWrite(Model model, CheckCommand checkCommand) {
		String checkNum = autoNumMapper.getAutoNum("check_", "7", "check_num", "checks");
		checkCommand.setCheckNum(checkNum);
		model.addAttribute("checkCommand", checkCommand);
		return "thymeleaf/check/checkWrite";
	}
	@PostMapping("checkWrite")
	public String checkWrite(@Validated CheckCommand checkCommand
			, BindingResult result
			, HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/check/checkWrite";
		}
		checkInsertService.execute(checkCommand, session);
		return "redirect:checkList";
	}
	
	@Autowired
	GoodsListService goodsListService;
	@GetMapping("goodsItem")
	public String goodsItem(@RequestParam(value="searchWord" , required = false) String searchWord
			, @RequestParam(value = "page" , required = false , defaultValue = "1") int page
			, Model model, HttpSession session) {
		goodsListService.execute(null, model, -1, session);
		return "thymeleaf/goodsIpgo/goodsItem";
	}
	@Autowired
	StoreListService storeListService;
	@GetMapping("storeSelect")
	public String storeSelect(Model model) {
		storeListService.execute(model);
		return "thymeleaf/book/storeSelect";
	}
	
	@GetMapping("checkUpdate")
	public String checkUpdate(String checkNum, Model model) {
		checkSelectService.execute(checkNum, model);
		return "thymeleaf/check/checkUpdate";
	}
	@PostMapping("checkUpdate")
	public String checkUpdate(@Validated CheckCommand checkCommand
			, BindingResult result
			, HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/check/checkUpdate";
		}
		checkUpdateService.execute(checkCommand, session);
		return "redirect:checkList";
	}
	@GetMapping("checkDelete")
	public String checkDelete(String checkNum) {
		checkDeleteService.execute(checkNum);
		return "redirect:checkList";
	}
	
}
