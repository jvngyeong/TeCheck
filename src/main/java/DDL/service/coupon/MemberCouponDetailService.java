package DDL.service.coupon;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.command.MemberCouponCommand;
import DDL.domain.CouponDetailDTO;
import DDL.mapper.CouponMapper;

@Service
public class MemberCouponDetailService {
	@Autowired
	CouponMapper couponMapper;
	
	public void execute(String issueNum, Model model) {
		CouponDetailDTO couponDetailDTO = couponMapper.memberCouponSelectOne(issueNum);
		MemberCouponCommand memberCouponCommand = new MemberCouponCommand();
		BeanUtils.copyProperties(couponDetailDTO.getCouponDTO(), memberCouponCommand);
		BeanUtils.copyProperties(couponDetailDTO.getMemberCouponDTO(), memberCouponCommand);
		model.addAttribute("memberCouponCommand", memberCouponCommand);
	}
}