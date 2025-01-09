package DDL.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.EmployeeMapper;
import DDL.mapper.MemberMapper;

@Service
public class GetUserIdService {
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	public String execute(String participantNum) {
		String userId = null;
		if(participantNum.substring(0, 3).equals("mem")) {
			userId = memberMapper.getMemberId(participantNum);
		}
		if(participantNum.substring(0, 3).equals("emp")) {
			userId = employeeMapper.getEmployeeId(participantNum);
		}
		return userId;
	}
}
