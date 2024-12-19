package DDL.service.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.DepartmentMapper;

@Service
public class DepartmentDeleteService {
	@Autowired
	DepartmentMapper departmentMapper;
	public void execute(String departmentNum) {
		departmentMapper.departmentDelete(departmentNum);
	}
}
