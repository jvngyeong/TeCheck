package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("deliveryDTO")
public class DeliveryDTO {
	String deliveryNum;
	String deliveryName;
	String deliveryStatus;
	Date deliveryDate;
	String orderNum;
}
