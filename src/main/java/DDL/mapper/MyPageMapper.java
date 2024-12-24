package DDL.mapper;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.EmployeeDTO;
import DDL.domain.MemberDTO;

@Mapper
public interface MyPageMapper {
	public MemberDTO memberSelectOne(String userId);
	public Integer memberUpdate(MemberDTO dto);
	public Integer memberDelete(String memberId);
	
	public EmployeeDTO empSelectOne(String userId);
	public String departmentSelect(String departmentNum);
	public Integer empUpdate(EmployeeDTO dto);
}
