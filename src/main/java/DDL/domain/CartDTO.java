package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("cartDTO")
public class CartDTO {
	String goodsNum;
	String memberNum;
	Date cartDate;
	String cartQty;
}
