package DDL.mapper;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.AuthInfoDTO;

@Mapper
public interface LoginMapper {
	public AuthInfoDTO loginSelectOne(String userId);
	
}
