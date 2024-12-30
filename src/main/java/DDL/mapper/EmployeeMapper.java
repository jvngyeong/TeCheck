package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.EmployeeDTO;

@Mapper
public interface EmployeeMapper {
	void employeeWrite(EmployeeDTO employeeDTO);

	List<EmployeeDTO> employeeListSelect();

	EmployeeDTO employeeSelectOne(String empNum);

	void employeeUpdate(EmployeeDTO employeeDTO);

	void employeeDelete(String empNum);

	String getEmpNum(String userId);
}
