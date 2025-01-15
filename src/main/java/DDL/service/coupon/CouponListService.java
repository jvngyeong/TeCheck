package DDL.service.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.CouponDTO;
import DDL.mapper.CouponMapper;

@Service
public class CouponListService {
	@Autowired
	CouponMapper couponMapper;
	public void execute(Model model) {
		List<CouponDTO> couponList = couponMapper.couponListSelect();
		model.addAttribute("couponList", couponList);
	}
}
