package DDL.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AutoNumMapper {

	String getAutoNum(String sep, String num, String column, String table);

}
