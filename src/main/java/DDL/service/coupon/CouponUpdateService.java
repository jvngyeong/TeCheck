package DDL.service.coupon;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.CouponCommand;
import DDL.domain.CouponDTO;
import DDL.mapper.CouponMapper;

@Service
public class CouponUpdateService {
	@Autowired
	CouponMapper couponMapper;
	public void execute(CouponCommand couponCommand) {
		CouponDTO couponDTO = new CouponDTO();
		BeanUtils.copyProperties(couponCommand, couponDTO);
		couponMapper.couponUpdate(couponDTO);
	}
}
