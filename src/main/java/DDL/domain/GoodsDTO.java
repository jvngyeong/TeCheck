package DDL.domain;



import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;
@Data
@Alias("goods")

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
	Date goodsRegist;
	Date goodsUpdateDate;
}
