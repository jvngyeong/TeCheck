package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.EmployeeCommand;
import DDL.service.employee.EmployeeDeleteService;
import DDL.service.employee.EmployeeDetailService;
import DDL.service.employee.EmployeeListService;
import DDL.service.employee.EmployeeUpdateService;
import DDL.service.employee.EmployeeWriteService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	EmployeeListService employeeListService;
	
	@Autowired
	EmployeeWriteService employeeWriteService;
	
	@Autowired
	EmployeeDetailService employeeDetailService;
	
	@Autowired
	EmployeeUpdateService employeeUpdateService;
	
	@Autowired
	EmployeeDeleteService employeeDeleteService;
	@GetMapping("employeeList")
	public String employeeList(Model model) {
		employeeListService.execute(model);
		return "thymeleaf/employee/employeeList";
	}
	
	@GetMapping("employeeWrite")
	public String employeeWrite() {
		return "thymeleaf/employee/employeeWrite";
	}
	
	@PostMapping("employeeWrite")
	public String employeeWrite(EmployeeCommand employeeCommand) {
		employeeWriteService.execute(employeeCommand);
		return "redirect:/employee/employeeList";
	}
	
	@GetMapping("employeeDetail")
	public String employeeDetail(String empNum, Model model) {
		employeeDetailService.execute(empNum, model);
		return "thymeleaf/employee/employeeDetail";
	}
	
	@GetMapping("employeeModify")
	public String employeeModify(String empNum, Model model) {
		employeeDetailService.execute(empNum, model);
		return "thymeleaf/employee/employeeModify";
	}
	
	@PostMapping("employeeModify")
	public String employeeModify(EmployeeCommand employeeCommand) {
		employeeUpdateService.execute(employeeCommand);
		return "redirect:/employee/employeeDetail?empNum="+employeeCommand.getEmpNum();
	}
	
	@GetMapping("employeeDelete")
	public String employeeDelete(String empNum) {
		employeeDeleteService.execute(empNum);
		return "redirect:/employee/employeeList";
	}
}
