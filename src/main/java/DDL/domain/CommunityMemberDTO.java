package DDL.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("communityMemberDTO")
public class CommunityMemberDTO {
	MemberDTO memberDTO;
	CommunityDTO communityDTO;
	EmployeeDTO employeeDTO;
}
