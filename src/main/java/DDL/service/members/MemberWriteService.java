package DDL.service.members;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import DDL.command.MemberCommand;
import DDL.domain.MemberDTO;
import DDL.mapper.AutoNumMapper;
import DDL.mapper.MemberMapper;

@Service
public class MemberWriteService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AutoNumMapper autoNumMapper;
	public void execute(MemberCommand memberCommand) {
		MemberDTO dto = new MemberDTO();
		BeanUtils.copyProperties(memberCommand, dto);
		String memberNum = autoNumMapper.getAutoNum("mem_", "5", "member_num", "members");
		dto.setMemberNum(memberNum);
		dto.setMemberPw(passwordEncoder.encode(memberCommand.getMemberPw()));
		memberMapper.memberInsert(dto);
	}
}
