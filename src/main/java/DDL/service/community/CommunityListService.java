package DDL.service.community;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.CommunityDTO;
import DDL.mapper.CommunityMapper;
@Service
public class CommunityListService {
	@Autowired
	CommunityMapper communityMapper;
	public void execute(Model model) {
		List<CommunityDTO> list = communityMapper.communityListSelect();
		model.addAttribute("CommList",list);
	}
}
