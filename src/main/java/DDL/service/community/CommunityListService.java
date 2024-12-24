package DDL.service.community;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.AuthInfoDTO;
import DDL.domain.CommunityDTO;
import DDL.mapper.CommunityMapper;
import jakarta.servlet.http.HttpSession;
@Service
public class CommunityListService {
	@Autowired
	CommunityMapper communityMapper;
	public void execute(Model model, HttpSession session) {
		List<CommunityDTO> list = communityMapper.communityListSelect();
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberId = auth.getUserId();
		model.addAttribute("memberId", memberId);
		model.addAttribute("commList", list);
	}
}
