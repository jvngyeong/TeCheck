package DDL.service.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.CouponDetailDTO;
import DDL.mapper.CouponMapper;

@Service
public class MemberCouponListService {
	@Autowired
	CouponMapper couponMapper;
	public void execute(Model model) {
		List<CouponDetailDTO> couponDetailList = couponMapper.memberCouponSelectList();
		model.addAttribute("couponDetailList", couponDetailList);
	}
}
