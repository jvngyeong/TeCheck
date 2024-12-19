package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.DepartmentCommand;
import DDL.service.department.DepartmentDeleteService;
import DDL.service.department.DepartmentDetailService;
import DDL.service.department.DepartmentListService;
import DDL.service.department.DepartmentUpdateService;
import DDL.service.department.DepartmentWriteService;

@Controller
@RequestMapping("department")
public class DepartmentController {
	@Autowired
	DepartmentWriteService departmentWriteService;
	
	@Autowired
	DepartmentListService departmentListService;
	
	@Autowired
	DepartmentDetailService departmentDetailService;
	
	@Autowired
	DepartmentUpdateService departmentUpdateService;
	
	@Autowired
	DepartmentDeleteService departmentDeleteService;
	
	@GetMapping("departmentList")
	public String departmentList(Model model) {
		departmentListService.execute(model);
		return "thymeleaf/department/departmentList";
	}
	
	@GetMapping("departmentWrite")
	public String departmentWrite() {
		return "thymeleaf/department/departmentWrite";
	}
	
	@PostMapping("departmentWrite")
	public String departmentWrite(DepartmentCommand departmentCommand) {
		departmentWriteService.execute(departmentCommand);
		return "redirect:/department/departmentList";
	}
	
	@GetMapping("departmentDetail")
	public String departmentDetail(String departmentNum, Model model) {
		departmentDetailService.execute(departmentNum, model);
		return "thymeleaf/department/departmentDetail";
	}
	
	@GetMapping("departmentModify")
	public String departmentModify(String departmentNum, Model model) {
		departmentDetailService.execute(departmentNum, model);
		return "thymeleaf/department/departmentModify";
	}
	
	@PostMapping("departmentModify")
	public String departmentModify(DepartmentCommand departmentCommand) {
		departmentUpdateService.execute(departmentCommand);
		return "redirect:/department/departmentDetail?departmentNum="+departmentCommand.getDepartmentNum();
	}
	
	@GetMapping("departmentDelete")
	public String departmentDelete(String departmentNum) {
		departmentDeleteService.execute(departmentNum);
		return "redirect:/department/departmentList";
	}
}
