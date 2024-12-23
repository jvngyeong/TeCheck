package DDL.service.members;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import DDL.command.MemberCommand;
import DDL.domain.MemberDTO;
import DDL.mapper.MemberMapper;

@Service
public class MemberWriteService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(MemberCommand memberCommand) {
		MemberDTO dto = new MemberDTO();
		BeanUtils.copyProperties(memberCommand, dto);
		dto.setMemberPw(passwordEncoder.encode(memberCommand.getMemberPw()));
		memberMapper.memberInsert(dto);
	}
}
