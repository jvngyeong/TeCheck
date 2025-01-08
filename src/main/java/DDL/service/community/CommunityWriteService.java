package DDL.service.community;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.CommunityCommand;
import DDL.domain.AuthInfoDTO;
import DDL.domain.CommunityDTO;
import DDL.mapper.AutoNumMapper;
import DDL.mapper.CommunityMapper;
import DDL.mapper.EmployeeMapper;
import DDL.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class CommunityWriteService {
	@Autowired
	CommunityMapper communityMapper;
	
	@Autowired
	AutoNumMapper autoNumMapper;
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(CommunityCommand communityCommand, HttpSession session) {
		CommunityDTO communityDTO = new CommunityDTO();
		BeanUtils.copyProperties(communityCommand, communityDTO);
		String commNum = autoNumMapper.getAutoNum("comm_", "6", "comm_num", "community");
		communityDTO.setCommNum(commNum);
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberNum = memberMapper.getMemberNum(auth.getUserId());
		communityDTO.setMemberNum(memberNum);
		if(memberNum == null) {
			String empNum = employeeMapper.getEmpNum(auth.getUserId());
			communityDTO.setEmpNum(empNum);
		}
		communityMapper.communityWrite(communityDTO);
	}
}
