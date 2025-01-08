package DDL.service.department;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.command.DepartmentCommand;
import DDL.domain.DepartmentDTO;
import DDL.mapper.DepartmentMapper;

@Service
public class DepartmentDetailService {
	@Autowired
	DepartmentMapper departmentMapper;
	public void execute(String departmentNum, Model model) {
		DepartmentCommand departmentCommand = new DepartmentCommand();
		DepartmentDTO departmentDTO = departmentMapper.departmentSelectOne(departmentNum);
		model.addAttribute("departmentDTO", departmentDTO);
		BeanUtils.copyProperties(departmentDTO, departmentCommand);
		model.addAttribute("departmentCommand", departmentCommand);
	}
}
