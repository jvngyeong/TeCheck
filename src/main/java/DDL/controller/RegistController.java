package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.MemberCommand;
import DDL.service.members.MemberWriteService;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("regist")
public class RegistController {
	@Autowired
	MemberWriteService memberWriteService;
	@GetMapping("registForm")
	public String registForm(Model model) {
		MemberCommand memberCommand = new MemberCommand();
		model.addAttribute("memberCommand", memberCommand);
		return "thymeleaf/regist/registForm";
	}
	
	@PostMapping("memberRegist")
	public String memberRegist(@Validated MemberCommand memberCommand
			,BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/regist/registForm";
		}
		memberWriteService.execute(memberCommand, result, session);
		return "redirect:/";
	}
}
