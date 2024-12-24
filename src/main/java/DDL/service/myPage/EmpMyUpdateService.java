package DDL.service.myPage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.EmployeeCommand;
import DDL.domain.EmployeeDTO;
import DDL.mapper.MyPageMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class EmpMyUpdateService {
	@Autowired
	MyPageMapper myPageMapper;
	public void execute(HttpSession session, EmployeeCommand employeeCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		BeanUtils.copyProperties(employeeCommand, dto);
		myPageMapper.empUpdate(dto);
	}
}
