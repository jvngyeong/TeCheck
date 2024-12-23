package DDL.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import DDL.command.LoginCommand;
import DDL.domain.AuthInfoDTO;
import DDL.mapper.MyPageMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MyPagePwConService {
	@Autowired
	MyPageMapper myPageMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public boolean execute(String page, HttpSession session, String userPw) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(passwordEncoder.matches(userPw, auth.getUserPw())) {
			return true;
		}else return false;
	}
}
