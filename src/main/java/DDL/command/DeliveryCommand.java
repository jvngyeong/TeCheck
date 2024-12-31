package DDL.command;

import lombok.Data;

@Data
public class DeliveryCommand {
	String deliveryNum;
	String deliveryName;
	String deliveryStatus;
	String deliveryDate;
	String orderNum;
}
