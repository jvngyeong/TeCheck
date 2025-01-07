package DDL.mapper;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.AuthInfoDTO;
import DDL.domain.LoginDTO;

@Mapper
public interface LoginMapper {
	public AuthInfoDTO loginSelectOne(String userId);

	public LoginDTO loginIdCheck(String value);

	public String registIdCheck(String memberId);
	
}
