package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.GoodsReviewDTO;
import DDL.domain.ReviewDTO;

@Mapper
public interface ReviewMapper {

	void reviewWrite(ReviewDTO reviewDTO);

	List<GoodsReviewDTO> selectReviewList();

	ReviewDTO reviewSelectOne(String orderNum, String goodsNum);

}
