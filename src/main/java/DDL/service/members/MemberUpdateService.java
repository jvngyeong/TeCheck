package DDL.service.members;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.MemberCommand;
import DDL.domain.MemberDTO;
import DDL.mapper.MemberMapper;

@Service
public class MemberUpdateService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(MemberCommand memberCommand) {
		MemberDTO dto = new MemberDTO();
		BeanUtils.copyProperties(memberCommand, dto);
		memberMapper.memberUpdate(dto);
	}
}
