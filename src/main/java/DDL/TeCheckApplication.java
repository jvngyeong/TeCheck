package DDL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.domain.AuthInfoDTO;
import DDL.domain.LoginDTO;
import DDL.mapper.LoginMapper;
import DDL.service.EmailConfService;
import DDL.service.EmailSendService;
import DDL.service.cart.CartListService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@SpringBootApplication
@Controller
public class TeCheckApplication {
	@Autowired
	EmailSendService emailSendService;
	
	@Autowired
	EmailConfService emailConfService;
	
	@Autowired
	CartListService cartListService;
	
	@Autowired
	LoginMapper loginMapper;
	
	public static void main(String[] args) {
		SpringApplication.run(TeCheckApplication.class, args);
	}

	@RequestMapping("/")
	public String index(Model model, HttpSession session, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies) {
			if(c.getName().equals("isAutoLogin")) {
				LoginDTO dto = loginMapper.loginIdCheck(c.getValue());
				AuthInfoDTO auth = new AuthInfoDTO();
				auth.setUserId(dto.getUserId());
				auth.setUserPw(dto.getUserPw());
				auth.setGrade(dto.getGrade());
				session.setAttribute("auth", auth);
			}
		}
		cartListService.execute(model, session);
		return "thymeleaf/index";
	}
	
	@GetMapping("/mailling")
	public String mail() {
		return "thymeleaf/email";
	}
	
	@PostMapping("/mailling")
	public String mail(String fromEmail, String toEmail, String subject, String contents) {
		emailSendService.mailSend(fromEmail, toEmail, subject, contents);
		return "redirect:/";
	}
	
	@GetMapping("/userConfirm")
	public String userConfirm(String chk) {
		emailConfService.execute(chk);
		return "thymeleaf/user/welcome";
	}
}
