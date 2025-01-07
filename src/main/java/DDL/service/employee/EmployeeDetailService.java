package DDL.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.DepartmentDTO;
import DDL.domain.EmployeeDTO;
import DDL.mapper.DepartmentMapper;
import DDL.mapper.EmployeeMapper;

@Service
public class EmployeeDetailService {
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	DepartmentMapper departmentMapper;
	public void execute(String empNum, Model model) {
		EmployeeDTO employeeDTO = employeeMapper.employeeSelectOne(empNum);
		DepartmentDTO departmentDTO = departmentMapper.departmentSelectOne(employeeDTO.getDepartmentNum());
		model.addAttribute("employeeDTO", employeeDTO);
		model.addAttribute("departmentName", departmentDTO.getDepartmentName());
	}
}
