package DDL.service.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.CouponMapper;

@Service
public class CouponDeleteService {
	@Autowired
	CouponMapper couponMapper;

	public void execute(String couponNum) {
		couponMapper.couponDelete(couponNum);
	}
}
