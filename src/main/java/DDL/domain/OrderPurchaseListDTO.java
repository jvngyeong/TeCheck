package DDL.domain;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("orderPurchaseListDTO")
public class OrderPurchaseListDTO {
	OrderDTO orderDTO;
	List<PurchaseListDTO> purchaseListDTO;
}