package DDL.command;

import lombok.Data;

@Data
public class OrderCommand {
	String orderNum;
	String[] goodsNums;
    Integer orderPrice;
    String deliveryAddr;
    String deliveryAddrDetail;
    String deliveryAddrPost;
    String deliveryPhone;
    String message;
    String memberNum;
    Integer totalPaymentPrice;
}
