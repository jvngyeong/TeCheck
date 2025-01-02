package DDL.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.MemberMapper;

@Service
public class EmailConfService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String memberEmail) {
		memberMapper.emailConfUpdate(memberEmail);
	}
}
