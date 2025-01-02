package DDL.domain;



import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Alias("goodsDTO")
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDTO {
	String goodsNum;
	String goodsName;
	Integer goodsPrice;
	String goodsContents;
	Integer visitCount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date goodsRegist;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date goodsUpdateDate;
	
	String goodsMainImage;
	String goodsDetailImage;
	String goodsMainStoreImage;
	String goodsDetailStoreImage;
	String goodsKind;
}
