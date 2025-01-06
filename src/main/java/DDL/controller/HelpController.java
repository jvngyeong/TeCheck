package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.FindCommand;
import DDL.service.help.IdFindService;
import DDL.service.help.PwChangeService;
import DDL.service.help.UserCheckService;

@Controller
@RequestMapping("help")
public class HelpController {
	@Autowired
	IdFindService idFindService;
	
	@Autowired
	UserCheckService userCheckService;
	
	@Autowired
	PwChangeService pwChangeService;
	
	@GetMapping("findId")
	public String findId() {
		return "thymeleaf/help/findId";
	}
	
	@PostMapping("findId")
	public String findId(FindCommand findCommand, Model model) {
		idFindService.execute(findCommand, model);
		return "thymeleaf/help/findIdResult";
	}
	
	@GetMapping("changePw")
	public String changePw() {
		return "thymeleaf/help/changePw";
	}
	
	@PostMapping("changePwForm")
	public String changePwForm(FindCommand findCommand, Model model) {
		userCheckService.execute(findCommand);
		model.addAttribute(findCommand);
		return "thymeleaf/help/changePwForm";
	}
	
	@PostMapping("changePw")
	public String changePw(FindCommand findCommand, Model model) {
		pwChangeService.execute(findCommand);
		return "thymeleaf/help/changePwResult";
	}
}
