package DDL.service.community;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.CommunityCommand;
import DDL.domain.CommunityDTO;
import DDL.mapper.CommunityMapper;

@Service
public class CommunityWriteService {
	@Autowired
	CommunityMapper communityMapper;
	public void execute(CommunityCommand communityCommand) {
		CommunityDTO communityDTO = new CommunityDTO();
		BeanUtils.copyProperties(communityCommand, communityDTO);
		communityDTO.setCommunityNum("comm_100001");
		communityDTO.setMemberNum("mem_100001");
		communityDTO.setCommunityTitle("111");
		communityDTO.setCommunityContents("222");
		communityDTO.setCommunityDate(null);
		communityMapper.communityWrite(communityDTO);
	}
}
