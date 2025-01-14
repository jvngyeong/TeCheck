package DDL.service.inquire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.domain.AuthInfoDTO;
import DDL.domain.InquireDTO;
import DDL.mapper.EmployeeMapper;
import DDL.mapper.InquireMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class InquireAnswerService {
	@Autowired
	InquireMapper inquireMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(InquireDTO inquireDTO, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String empNum = employeeMapper.getEmpNum(auth.getUserId());
		inquireDTO.setEmpNum(empNum);
		inquireMapper.inquireAnswerUpdate(inquireDTO);
	}
}
