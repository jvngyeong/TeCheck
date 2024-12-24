package DDL.service.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.CommunityMapper;

@Service
public class CommunityDeleteService {
	@Autowired
	CommunityMapper communityMapper;
	public void execute(String commNum) {
		communityMapper.communityDelete(commNum);
	}
}
