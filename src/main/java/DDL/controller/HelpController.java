package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String findId(Model model) {
		FindCommand findCommand = new FindCommand();
		model.addAttribute("findCommand", findCommand);
		return "thymeleaf/help/findId";
	}
	
	@PostMapping("findId")
	public String findId(@Validated FindCommand findCommand, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "thymeleaf/help/findId";
		}
		idFindService.execute(findCommand, model);
		return "thymeleaf/help/findIdResult";
	}
	
	@GetMapping("changePw")
	public String changePw(Model model) {
		FindCommand findCommand = new FindCommand();
		model.addAttribute("findCommand", findCommand);
		return "thymeleaf/help/changePw";
	}
	
	@PostMapping("changePwForm")
	public String changePwForm(@Validated FindCommand findCommand, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "thymeleaf/help/changePw";
		}
		String grade = userCheckService.execute(findCommand);
		if(grade == null) {
			result.rejectValue("userId", "findCommand.userId", "* 입력하신 정보에 해당하는 회원이 존재하지 않습니다.");
			return "thymeleaf/help/changePw";
		}
		findCommand.setGrade(grade);
		model.addAttribute(findCommand);
		return "thymeleaf/help/changePwForm";
	}
	
	@PostMapping("changePw")
	public @ResponseBody String changePw(FindCommand findCommand, Model model) {
		pwChangeService.execute(findCommand);
		return "/";
	}
}
