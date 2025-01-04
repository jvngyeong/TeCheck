package DDL.service.community;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.CommunityReplyDTO;
import DDL.mapper.AutoNumMapper;
import DDL.mapper.CommunityMapper;

@Service
public class CommunityReplyListService {
	@Autowired
	CommunityMapper communityMapper;
	@Autowired
	AutoNumMapper autoNumMapper;

	public void execute(Model model) {
		List<CommunityReplyDTO> list = communityMapper.communityReplyListSelect();
	}
	
}
