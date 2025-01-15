package DDL.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("couponDTO")
public class CouponDTO {
	String couponNum;
	String couponName;
	Integer discount;
}
