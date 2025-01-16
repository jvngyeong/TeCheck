package DDL.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("couponDetailDTO")

public class CouponDetailDTO {
	CouponDTO couponDTO;
	MemberCouponDTO memberCouponDTO;
}
