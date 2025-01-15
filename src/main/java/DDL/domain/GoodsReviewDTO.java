package DDL.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("goodsReviewDTO")
public class GoodsReviewDTO {
	GoodsDTO goodsDTO;
	ReviewDTO reviewDTO;
	OrderDTO orderDTO;
	MemberDTO memberDTO;
}
