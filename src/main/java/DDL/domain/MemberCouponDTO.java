package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("memberCouponDTO")
public class MemberCouponDTO {
	String couponNum;
	String memberNum;
	Date couponStart;
	Date couponExpire;
	String empNum;
}
