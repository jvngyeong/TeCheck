package DDL.service.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.CouponMapper;

@Service
public class MemberCouponDeleteService {
	@Autowired
	CouponMapper couponMapper;
	public void execute(String issueNum) {
		couponMapper.memberCouponDelete(issueNum);
	}
}
