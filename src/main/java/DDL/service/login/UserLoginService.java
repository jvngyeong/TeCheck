package DDL.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import DDL.command.LoginCommand;
import DDL.domain.AuthInfoDTO;
import DDL.mapper.LoginMapper;
import DDL.mapper.MemberMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class UserLoginService {
	@Autowired
	LoginMapper loginMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberMapper memberMapper;
	public void execute(LoginCommand loginCommand
			, HttpSession session
			, BindingResult result
			, HttpServletResponse response) {
		AuthInfoDTO auth = loginMapper.loginSelectOne(loginCommand.getUserId());
		if(auth != null) {
			if(passwordEncoder.matches(loginCommand.getUserPw()
					, auth.getUserPw())) {
				if(auth.getGrade().equals("mem")) {
					String emailConf = memberMapper.emailConfCheck(auth.getUserId());
					if(emailConf == null) {
						result.rejectValue("userId", "loginCommand.userPw"
								, "가입 확인 이메일을 확인해주세요.");	
					}
				}
				session.setAttribute("auth", auth);
			}else {
				result.rejectValue("userPw", "loginCommand.userPw"
						, "비밀번호가 틀렸습니다.");	
			}
		}else {
			if(loginCommand.getUserId() != "") {
				result.rejectValue("userId", "loginCommand.userId"
						, "아이디가 존재하지 않습니다.");
			}
		}
	}
}
