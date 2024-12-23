package DDL.service.employee;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import DDL.command.EmployeeCommand;
import DDL.domain.EmployeeDTO;
import DDL.mapper.AutoNumMapper;
import DDL.mapper.EmployeeMapper;

@Service
public class EmployeeWriteService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AutoNumMapper autoNumMapper;
	public void execute(EmployeeCommand employeeCommand) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(employeeCommand, employeeDTO);
		String empNum = autoNumMapper.getAutoNum("emp_", "5", "emp_num", "employees");
		employeeDTO.setEmpNum(empNum);
		employeeDTO.setEmpPw(passwordEncoder.encode(employeeCommand.getEmpPw()));
		employeeDTO.setEmpImage("11");
		employeeDTO.setEmpStoreImage("22");	
		employeeMapper.employeeWrite(employeeDTO);
	}
}
