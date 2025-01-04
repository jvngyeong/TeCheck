package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.LoginCommand;
import DDL.service.login.UserLoginService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	UserLoginService userLoginService;
	@GetMapping("loginForm")
	public String loginForm(Model model) {
		model.addAttribute("loginCommand", new LoginCommand()); // LoginCommand 클래스 생성 필요
		return "thymeleaf/login/loginForm";
	}
	@PostMapping("login")
	public String login(@Validated LoginCommand loginCommand
			,BindingResult result
			,HttpSession session
			,HttpServletResponse response) {
		userLoginService.execute(loginCommand, session, result, response);
		if(result.hasErrors()) {
			return "thymeleaf/login/loginForm";
		}
		return "redirect:/";
	}
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
