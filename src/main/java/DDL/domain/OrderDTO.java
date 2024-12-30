package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("orderDTO")
public class OrderDTO {
	String orderNum;
    Date orderDate;
    Integer orderPrice;
    String deliveryAddr;
    String deliveryAddrDetail;
    String deliveryAddrPost;
    String deliveryPhone;
    String message;
    String orderStatus;
    String memberNum;
}
