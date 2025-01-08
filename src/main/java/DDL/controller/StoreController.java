package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.StoreCommand;
import DDL.service.store.StoreDeleteService;
import DDL.service.store.StoreDetailService;
import DDL.service.store.StoreListService;
import DDL.service.store.StoreUpdateService;
import DDL.service.store.StoreWriteService;



@Controller
@RequestMapping("store")
public class StoreController {
	@Autowired
	StoreWriteService storeWriteService;
	@Autowired
	StoreListService storeListService;
	@Autowired
	StoreDetailService storeDetailService;
	@Autowired
	StoreUpdateService storeUpdateService;
	@Autowired
	StoreDeleteService storeDeleteService;
	@GetMapping("storeList")
	public String storeList(Model model) {
		storeListService.execute(model);
		return "thymeleaf/store/storeList";
	}
	@GetMapping("storeWrite")
	public String storeWrite(Model model) {
		StoreCommand storeCommand = new StoreCommand();
		model.addAttribute("storeCommand", storeCommand);
		return "thymeleaf/store/storeWrite";
	}
	@PostMapping("storeWrite")
	public String storeWrite(@Validated StoreCommand storeCommand
			, BindingResult result) {
		if (result.hasErrors()) {
		    return "thymeleaf/store/storeWrite";
		}
		storeWriteService.execute(storeCommand);
		return "redirect:storeList";
	}
	@GetMapping("storeDetail")
	public String storeDetail(String storeNum, Model model) {
		storeDetailService.execute(storeNum, model);
		return "thymeleaf/store/storeDetail";
	}
	@GetMapping("storeModify")
	public String storeModify(String storeNum, Model model) {
		storeDetailService.execute(storeNum, model);
		return "thymeleaf/store/storeModify";
	}
	@PostMapping("storeModify")
	public String storeModify(@Validated StoreCommand storeCommand
			, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("storeCommand", storeCommand);
			return "thymeleaf/store/storeModify";
		}
		storeUpdateService.execute(storeCommand);
		return "redirect:storeDetail?storeNum=" + storeCommand.getStoreNum();
	}
	@GetMapping("storeDelete")
	public String storeDelete(String storeNum) {
		storeDeleteService.execute(storeNum);
		return "redirect:storeList";
	}
	
}
