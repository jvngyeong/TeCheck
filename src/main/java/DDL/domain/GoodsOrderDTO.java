package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("goodsOrderDTO")
public class GoodsOrderDTO {
	String goodsOrderNum;
	Date goodsOrderDate;
	String empNum;
	String contractNum;
	String supNum;
	Integer goodsOrderQty;
}
