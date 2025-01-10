package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("reviewDTO")
public class ReviewDTO {
	String reviewNum;
	String bookNum;
	Date reviewDate;
	String reviewContent;
	String orderNum;
	String goodsNum;
}
