package DDL.service.community;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.AuthInfoDTO;
import DDL.domain.CommunityDTO;
import DDL.domain.CommunityReplyDTO;
import DDL.mapper.CommunityMapper;
import jakarta.servlet.http.HttpSession;
import spring_boot_board.mapper.AutoNumMapper;

@Service
public class CommunityReplyListService {
	@Autowired
	CommunityMapper communityMapper;
	@Autowired
	AutoNumMapper autoNumMapper;

	public void execute(Model model) {
		List<CommunityReplyDTO> list = communityMapper.communityReplyListSelect();
		String replyNum = autoNumMapper.getAutoNym()
	}
	
}
