package DDL.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.AuthInfoDTO;
import DDL.domain.MemberDTO;
import DDL.mapper.MyPageMapper;
import jakarta.servlet.http.HttpSession;
@Service
public class MemberMyPageService {
	@Autowired
	MyPageMapper myPageMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberId = auth.getUserId();
		MemberDTO dto = myPageMapper.memberSelectOne(memberId);
		model.addAttribute("memberCommand", dto);
	}
}
