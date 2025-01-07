package DDL.service.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.CommunityMemberDTO;
import DDL.mapper.CommunityMapper;

@Service
public class CommunityDetailService {
	@Autowired
	CommunityMapper communityMapper;
	public void execute(String commNum, Model model) {
		CommunityMemberDTO communityMemberDTO = communityMapper.communitySelectOne(commNum);
		model.addAttribute("communityMemberDTO", communityMemberDTO);
	}
}
