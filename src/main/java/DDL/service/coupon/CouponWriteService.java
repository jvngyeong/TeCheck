package DDL.service.coupon;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.CouponCommand;
import DDL.domain.CouponDTO;
import DDL.mapper.AutoNumMapper;
import DDL.mapper.CouponMapper;

@Service
public class CouponWriteService {
	@Autowired
	CouponMapper couponMapper;
	
	@Autowired
	AutoNumMapper autoNumMapper;
	public void execute(CouponCommand couponCommand) {
		CouponDTO couponDTO = new CouponDTO();
		BeanUtils.copyProperties(couponCommand, couponDTO);
		String couponNum = autoNumMapper.getAutoNum("coupon_", "8", "coupon_num", "coupon");
		couponDTO.setCouponNum(couponNum);
		couponMapper.couponInsert(couponDTO);
	}
}
