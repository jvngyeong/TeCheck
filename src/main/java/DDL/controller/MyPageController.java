package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.LoginCommand;
import DDL.service.myPage.EmpMyPageService;
import DDL.service.myPage.MemberMyPageService;
import DDL.service.myPage.MyPagePwConService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("myPage")
public class MyPageController {
	@Autowired
	EmpMyPageService empMyPageService;
	@Autowired
	MemberMyPageService memberMyPageService;
	@Autowired
	MyPagePwConService myPagePwConService;
	@GetMapping("empMyPage")
	public String empMyPage(HttpSession session, Model model) {
		empMyPageService.execute(session, model);
		return "thymeleaf/myPage/empMyPage";
	}
	@GetMapping("memberMyPage")
	public String memberMyPage(HttpSession session, Model model) {
		memberMyPageService.execute(session, model);
		return "thymeleaf/myPage/memberMyPage";
	}
	@GetMapping("myPagePwCon")
	public String myPagePwCon(String page, Model model) {
		model.addAttribute("page", page);
		return "thymeleaf/myPage/myPagePwCon";
	}
	@PostMapping("myPagePwCon")
	public String myPagePwCon(String page, HttpSession session, String userPw) {
		boolean pwCon = myPagePwConService.execute(page, session, userPw);
		if (pwCon == true) {
			return "redirect:"+page;
		}else {
			return "redirect:/myPage/myPagePwCon?page="+page;
		}
	}
	
	@GetMapping("memberModify")
	public String memberModify(HttpSession session, Model model) {
		memberMyPageService.execute(session, model);
		return "thymeleaf/myPage/memberModify";
	}
	@PostMapping("memberUpdate")
	public String memberUpdate() {
		
		return "redirect:memberMyPage";
	}
}
