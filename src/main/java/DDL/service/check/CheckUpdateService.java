package DDL.service.check;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.CheckCommand;
import DDL.domain.AuthInfoDTO;
import DDL.domain.CheckDTO;
import DDL.mapper.CheckMapper;
import DDL.mapper.EmployeeMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class CheckUpdateService {
	@Autowired
	CheckMapper checkMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(CheckCommand checkCommand, HttpSession session) {
		CheckDTO dto = new CheckDTO();
		BeanUtils.copyProperties(checkCommand, dto);
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String empNum = employeeMapper.getEmpNum(auth.getUserId());
		dto.setEmpNum(empNum);
		
		checkMapper.checkUpdate(dto);
	}

}
