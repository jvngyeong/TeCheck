package DDL.service.coupon;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.command.CouponCommand;
import DDL.domain.CouponDTO;
import DDL.mapper.CouponMapper;

@Service
public class CouponDetailService {
	@Autowired
	CouponMapper couponMapper;

	public void execute(String couponNum, Model model) {
		CouponDTO couponDTO = couponMapper.couponSelectOne(couponNum);
		CouponCommand couponCommand = new CouponCommand();
		BeanUtils.copyProperties(couponDTO, couponCommand);
		model.addAttribute("couponCommand", couponCommand);
	}
}
