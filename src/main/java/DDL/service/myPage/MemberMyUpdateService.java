package DDL.service.myPage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.MemberCommand;
import DDL.domain.AuthInfoDTO;
import DDL.domain.MemberDTO;
import DDL.mapper.MyPageMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberMyUpdateService {
	@Autowired
	MyPageMapper myPageMapper;
	public void execute(HttpSession session, MemberCommand memberCommand) {
		MemberDTO dto = new MemberDTO();
		BeanUtils.copyProperties(memberCommand, dto);
		myPageMapper.memberUpdate(dto);
	}
}
