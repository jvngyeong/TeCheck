package DDL.domain;



import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
@Alias("goodsDTO")
public class GoodsDTO {
	String goodsNum;
	String goodsName;
	Integer goodsPrice;
	String goodsContents;
	Integer visitCount;
	String goodsMainImage;
	String goodsDetailImage;
	String goodsMainStoreImage;
	String goodsDetailStoreImage;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date goodsRegist;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date goodsUpdateDate;
}
