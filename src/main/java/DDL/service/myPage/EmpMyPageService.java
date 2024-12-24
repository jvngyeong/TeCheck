package DDL.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.AuthInfoDTO;
import DDL.domain.EmployeeDTO;
import DDL.mapper.MyPageMapper;
import jakarta.servlet.http.HttpSession;
@Service
public class EmpMyPageService {
	@Autowired
	MyPageMapper myPageMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String empId = auth.getUserId();
		EmployeeDTO dto = myPageMapper.empSelectOne(empId);
		String departmentName = myPageMapper.departmentSelect(dto.getDepartmentNum());
		model.addAttribute("departmentName", departmentName);
		model.addAttribute("employeeCommand", dto);
	}

}
