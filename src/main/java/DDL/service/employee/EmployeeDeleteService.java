package DDL.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.EmployeeMapper;

@Service
public class EmployeeDeleteService {
    @Autowired
    EmployeeMapper employeeMapper;
    public void execute(String empNum) {
        employeeMapper.employeeDelete(empNum);
    }
}