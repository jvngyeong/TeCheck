package DDL.service.community;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.CommunityCommand;
import DDL.domain.CommunityDTO;
import DDL.mapper.CommunityMapper;

@Service
public class CommunityCommentService {
	@Autowired
	CommunityMapper communityMapper;
	public void execute(CommunityCommand communityCommand) {
	
		
	}


}
