package DDL.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.EmployeeDTO;
import DDL.mapper.EmployeeMapper;

@Service
public class EmployeeDetailService {
    @Autowired
    EmployeeMapper employeeMapper;
    public void execute(String empNum, Model model) {
        EmployeeDTO employeeDTO = employeeMapper.employeeSelectOne(empNum);
        model.addAttribute("employeeDTO", employeeDTO);
    }
}