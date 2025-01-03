package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import DDL.domain.BookDTO;
import DDL.domain.BookStoreGoodsExpDTO;

@Mapper
public interface BookMapper {
	public Integer bookInsert(BookDTO dto);

	public List<BookStoreGoodsExpDTO> bookSelectMine(String memberNum);

	public BookStoreGoodsExpDTO bookSelectInfo(
			@Param("bookNum") String bookNum
			, @Param("memberNum") String memberNum);

	public Integer bookUpdate(BookDTO dto);
}
