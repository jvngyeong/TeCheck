package DDL.service.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.DepartmentDTO;
import DDL.mapper.DepartmentMapper;

@Service
public class DepartmentDetailService {
	@Autowired
	DepartmentMapper departmentMapper;
	public void execute(String departmentNum, Model model) {
		DepartmentDTO departmentDTO = departmentMapper.departmentSelectOne(departmentNum);
		model.addAttribute("departmentDTO", departmentDTO);
	}
}
