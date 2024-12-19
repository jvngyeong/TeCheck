package DDL.service.employee;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.EmployeeCommand;
import DDL.domain.EmployeeDTO;
import DDL.mapper.EmployeeMapper;

@Service
public class EmployeeWriteService {
    @Autowired
    EmployeeMapper employeeMapper;
    public void execute(EmployeeCommand employeeCommand) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employeeCommand, employeeDTO);
        employeeDTO.setEmpNum("emp_100001");
        employeeDTO.setDepartmentNum("dep_100001");
        employeeDTO.setEmpImage("11");
        employeeDTO.setEmpStoreImage("22");
        employeeMapper.employeeWrite(employeeDTO);
    }
}