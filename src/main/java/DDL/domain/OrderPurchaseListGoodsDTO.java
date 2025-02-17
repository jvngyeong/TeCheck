package DDL.domain;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("orderPurchaseListGoodsDTO")
public class OrderPurchaseListGoodsDTO {
	OrderDTO orderDTO;
	PayDTO payDTO;
	DeliveryDTO deliveryDTO;
	List<PurchaseListDTO> purchaseListDTO;
	List<GoodsDTO> goodsDTO;
}
