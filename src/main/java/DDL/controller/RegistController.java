package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.MemberCommand;
import DDL.service.members.MemberWriteService;


@Controller
@RequestMapping("regist")
public class RegistController {
	@Autowired
	MemberWriteService memberWriteService;
	@GetMapping("registForm")
	public String registForm() {
		return "thymeleaf/regist/registForm";
	}
	@PostMapping("memberRegist")
	public String memberRegist(MemberCommand memberCommand) {
		memberWriteService.execute(memberCommand);
		return "redirect:/";
	}
	
	
}
