package DDL.mapper;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.MemberDTO;

@Mapper
public interface MyPageMapper {
	public MemberDTO memberSelectOne(String userId);
}
