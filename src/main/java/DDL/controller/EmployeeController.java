package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.EmployeeCommand;
import DDL.service.department.DepartmentListService;
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
	
	@Autowired
	DepartmentListService departmentListService;
	
	@GetMapping("employeeList")
	public String employeeList(Model model) {
		employeeListService.execute(model);
		return "thymeleaf/employee/employeeList";
	}
	
	@GetMapping("employeeWrite")
	public String employeeWrite(Model model) {
		EmployeeCommand employeeCommand = new EmployeeCommand();
		model.addAttribute("employeeCommand", employeeCommand);
		departmentListService.execute(model);
		return "thymeleaf/employee/employeeWrite";
	}
	
	@PostMapping("employeeWrite")
	public String employeeWrite(@Validated EmployeeCommand employeeCommand, BindingResult result, Model model) {
		if(result.hasErrors()) {
			departmentListService.execute(model);
			return "thymeleaf/employee/employeeWrite";
		}
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
		EmployeeCommand employeeCommand = new EmployeeCommand();
		model.addAttribute("employeeCommand", employeeCommand);
		departmentListService.execute(model);
		employeeDetailService.execute(empNum, model);
		return "thymeleaf/employee/employeeModify";
	}
	
	@PostMapping("employeeModify")
	public String employeeModify(@Validated EmployeeCommand employeeCommand, BindingResult result, Model model) {
		System.out.println(employeeCommand.getDepartmentNum()+"입니다.");
		if(result.hasErrors()) {
			System.out.println(result.getFieldError());
			departmentListService.execute(model);
			employeeDetailService.execute(employeeCommand.getEmpNum(), model);
			return "thymeleaf/employee/employeeModify";
		}
		employeeUpdateService.execute(employeeCommand);
		return "redirect:/employee/employeeDetail?empNum="+employeeCommand.getEmpNum();
	}
	
	@GetMapping("employeeDelete")
	public String employeeDelete(String empNum) {
		employeeDeleteService.execute(empNum);
		return "redirect:/employee/employeeList";
	}
}
