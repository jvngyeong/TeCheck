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
import DDL.service.members.MemberDeleteService;
import DDL.service.members.MemberDetailService;
import DDL.service.members.MemberListService;
import DDL.service.members.MemberUpdateService;
import DDL.service.members.MemberWriteService;
import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	MemberWriteService memberWriteService;
	@Autowired
	MemberListService memberListService;
	@Autowired 
	MemberDetailService memberDetailService;
	@Autowired
	MemberUpdateService memberUpdateService;
	@Autowired
	MemberDeleteService memberDeleteService;
	@GetMapping("memberList")
	public String memberList(Model model) {
		memberListService.execute(model);
		return "thymeleaf/member/memberList";
	}
	@GetMapping("memberWrite")
	public String memberWrite(Model model) {
		MemberCommand memberCommand = new MemberCommand();
		model.addAttribute("memberCommand", memberCommand);
		return "thymeleaf/member/memberForm";
	}
	@PostMapping("memberWrite")
	public String memberWrite(@Validated MemberCommand memberCommand, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/member/memberForm";
		}
		memberWriteService.execute(memberCommand, result, session);
		return "redirect:memberList";
	}
	
	@GetMapping("memberDetail")
	public String memberDetail(Model model, String memberNum) {
		memberDetailService.execute(model, memberNum);
		return "thymeleaf/member/memberDetail";
	}
	@GetMapping("memberModify")
	public String memberModify(Model model, String memberNum) {
		MemberCommand memberCommand = new MemberCommand();
		model.addAttribute("memberCommand", memberCommand);
		memberDetailService.execute(model, memberNum);
		return "thymeleaf/member/memberModify";
	}
	@PostMapping("memberUpdate")
	public String memberUpdate(@Validated MemberCommand memberCommand, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("memberCommand", memberCommand);
			return "thymeleaf/member/memberModify";
		}
		memberUpdateService.execute(memberCommand);
		return "redirect:memberDetail?memberNum=" + memberCommand.getMemberNum();
	}
	@GetMapping("memberDelete")
	public String memberDelete(String memberNum) {
		memberDeleteService.execute(memberNum);
		return "redirect:memberList";
	}
}
