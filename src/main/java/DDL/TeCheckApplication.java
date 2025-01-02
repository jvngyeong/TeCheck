package DDL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.service.EmailConfService;
import DDL.service.EmailSendService;

@SpringBootApplication
@Controller
public class TeCheckApplication {
	@Autowired
	EmailSendService emailSendService;
	
	@Autowired
	EmailConfService emailConfService;
	
	public static void main(String[] args) {
		SpringApplication.run(TeCheckApplication.class, args);
	}

	@RequestMapping("/")
	public String index() {
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
