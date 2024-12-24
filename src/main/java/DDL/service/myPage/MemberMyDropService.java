package DDL.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.domain.AuthInfoDTO;
import DDL.mapper.MyPageMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberMyDropService {
	@Autowired
	MyPageMapper myPageMapper;
	public void execute(HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberId = auth.getUserId();
		myPageMapper.memberDelete(memberId);
		session.invalidate();
	}
}
