package DDL.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("purchaseListDTO")
public class PurchaseListDTO {
	String orderNum;
	String goodsNum;
	Integer purchaseQty;
	Integer goodsUnitPrice;
}
