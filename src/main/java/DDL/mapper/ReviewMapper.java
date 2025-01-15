package DDL.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.GoodsReviewDTO;
import DDL.domain.ReviewDTO;

@Mapper
public interface ReviewMapper {

	void reviewWrite(ReviewDTO reviewDTO);

	ReviewDTO reviewSelectOne(String orderNum, String goodsNum);

	List<GoodsReviewDTO> selectReviewList(Map<String, Object> map);

	void reviewDelete(String reviewNum);


}
