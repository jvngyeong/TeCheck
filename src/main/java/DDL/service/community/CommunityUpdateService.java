package DDL.service.community;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.CommunityCommand;
import DDL.domain.CommunityDTO;
import DDL.mapper.CommunityMapper;

@Service
public class CommunityUpdateService {
	@Autowired
	CommunityMapper communityMapper;
	public void execute(CommunityCommand communityCommand) {
		CommunityDTO communityDTO = new CommunityDTO();
		BeanUtils.copyProperties(communityCommand, communityDTO);
		communityMapper.communityUpdate(communityDTO);
	}
}
