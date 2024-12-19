package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.DepartmentDTO;

@Mapper
public interface DepartmentMapper {
	void departmentWrite(DepartmentDTO departmentDTO);

	List<DepartmentDTO> departmentSelectList();

	DepartmentDTO departmentSelectOne(String departmentNum);

	void departmentUpdate(DepartmentDTO departmentDTO);

	void departmentDelete(String departmentNum);
}