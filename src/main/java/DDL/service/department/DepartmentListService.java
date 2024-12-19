package DDL.service.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.DepartmentDTO;
import DDL.mapper.DepartmentMapper;

@Service
public class DepartmentListService {
	@Autowired
	DepartmentMapper departmentMapper;
	public void execute(Model model) {
		List<DepartmentDTO> departmentList = departmentMapper.departmentSelectList();
		model.addAttribute("departmentList", departmentList);
	}
}
