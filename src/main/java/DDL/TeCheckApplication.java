package DDL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class TeCheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeCheckApplication.class, args);
	}

	@RequestMapping("/")
	public String index() {
		return "thymeleaf/index";
	}
}
