package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.ManageCommand;
import DDL.mapper.AutoNumMapper;
import DDL.service.manage.ManageDeleteService;
import DDL.service.manage.ManageInsertService;
import DDL.service.manage.ManageListService;
import DDL.service.manage.ManageSelectService;
import DDL.service.manage.ManageUpdateService;
import DDL.service.store.StoreListService;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("manage")
public class ManageController {
	@Autowired
	AutoNumMapper autoNumMapper;
	
	@Autowired
	ManageInsertService manageInsertService;
	
	@Autowired
	ManageListService manageListService;
	
	@Autowired
	ManageSelectService manageSelectService;
	
	@Autowired
	ManageUpdateService manageUpdateService;
	
	@Autowired
	ManageDeleteService manageDeleteService;
	
	@GetMapping("manageList")
	public String manageList(Model model) {
		manageListService.execute(model);
		return "thymeleaf/manage/manageList";
	}
	@GetMapping("manageWrite")
	public String managerWrite(Model model, ManageCommand manageCommand) {
		String manageNum = autoNumMapper.getAutoNum("manage_", "8", "manage_num", "manage");
		manageCommand.setManageNum(manageNum);
		model.addAttribute("manageCommand", manageCommand);
		return "thymeleaf/manage/manageWrite";
	}
	@PostMapping("manageWrite")
	public String managerWrite(@Validated ManageCommand manageCommand
			, BindingResult result
			, HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/manage/manageWrite";
		}
		manageInsertService.execute(manageCommand, session);
		return "redirect:manageList";
	}
	
	@Autowired
	StoreListService storeListService;
	@GetMapping("storeItem")
	public String storeItem(Model model) {
		storeListService.execute(model);
		return "thymeleaf/manage/storeItem";
	}
	
	@GetMapping("manageUpdate")
	public String manageUpdate(String manageNum, Model model) {
		manageSelectService.execute(manageNum, model);
		return "thymeleaf/manage/manageUpdate";
	}
	@PostMapping("manageUpdate")
	public String manageUpdate(@Validated ManageCommand manageCommand
			, BindingResult result
			, HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/manage/manageUpdate";
		}
		manageUpdateService.execute(manageCommand, session);
		return "redirect:manageList";
	}
	@GetMapping("manageDelete")
	public String manageDelete(String manageNum) {
		manageDeleteService.execute(manageNum);
		return "redirect:manageList";
	}
	
}
