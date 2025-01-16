package DDL.service.coupon;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.MemberCouponCommand;
import DDL.domain.MemberCouponDTO;
import DDL.mapper.CouponMapper;

@Service
public class MemberCouponUpdateService {
	@Autowired
	CouponMapper couponMapper;
	public void execute(MemberCouponCommand memberCouponCommand) {
		MemberCouponDTO memberCouponDTO = new MemberCouponDTO();
		BeanUtils.copyProperties(memberCouponCommand, memberCouponDTO);
		couponMapper.memberCouponUpdate(memberCouponDTO);
	}
}
