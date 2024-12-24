package DDL.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WishMapper {

	void wishMerge(String goodsNum, String memberNum);

	String wishCheck(String goodsNum, String memberNum);

}
