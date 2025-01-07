package DDL.service.members;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import DDL.command.MemberCommand;
import DDL.domain.AuthInfoDTO;
import DDL.domain.MemberDTO;
import DDL.mapper.AutoNumMapper;
import DDL.mapper.LoginMapper;
import DDL.mapper.MemberMapper;
import DDL.service.EmailSendService;
import DDL.service.SMSMessageService;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberWriteService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AutoNumMapper autoNumMapper;
	@Autowired
	EmailSendService emailSendService;
	@Autowired
	SMSMessageService sMSMessageService;
	@Autowired
	LoginMapper loginMapper;
	public void execute(MemberCommand memberCommand, BindingResult result, HttpSession session) {
		String errorCode = loginMapper.registIdCheck(memberCommand.getMemberId());
		if(errorCode == null) {
			MemberDTO dto = new MemberDTO();
			BeanUtils.copyProperties(memberCommand, dto);
			String memberNum = autoNumMapper.getAutoNum("mem_", "5", "member_num", "members");
			dto.setMemberNum(memberNum);
			dto.setMemberPw(passwordEncoder.encode(memberCommand.getMemberPw()));
			int i = memberMapper.memberInsert(dto);
			
			AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
			if(auth == null && i > 0) {
				String contents = "<html><body>";
				   contents += dto.getMemberName() + "님 가입을 환영합니다.<br/>";
				   contents += "가입을 완료하시려면 ";
				   contents += "<a href = 'http://localhost:8080/userConfirm?chk="+dto.getMemberEmail()+"'>여기</a>를 클릭하세요.";
				String subject = "TeCheck 가입 확인 메일입니다.";
				String fromEmail = "highland0@nate.com"; //요거는 안중요해요 어차피 강사님걸로 날아감
				String toEmail = dto.getMemberEmail();
				emailSendService.mailSend(fromEmail, toEmail, subject, contents);
				sMSMessageService.smsSend(dto.getMemberPhone(), "010-7146-1970", "[TeCheck] 가입 시 기입한 이메일을 확인해주세요.");
			}
		}
		else {
			result.rejectValue("memberId", "memberCommand.memberId","* 이미 사용중인 아이디입니다.");
		}
	}
}
