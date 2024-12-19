package DDL.service.department;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.DepartmentCommand;
import DDL.domain.DepartmentDTO;
import DDL.mapper.DepartmentMapper;

@Service
public class DepartmentWriteService {
	@Autowired
	DepartmentMapper departmentMapper;
	public void execute(DepartmentCommand departmentCommand) {
		DepartmentDTO departmentDTO = new DepartmentDTO();
		BeanUtils.copyProperties(departmentCommand, departmentDTO);
		departmentMapper.departmentWrite(departmentDTO);
	}
}
