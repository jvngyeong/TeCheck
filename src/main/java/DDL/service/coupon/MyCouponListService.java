package DDL.service.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.AuthInfoDTO;
import DDL.domain.CouponDetailDTO;
import DDL.mapper.CouponMapper;
import DDL.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MyCouponListService {
	@Autowired
	CouponMapper couponMapper;
	
	@Autowired
	MemberMapper memberMapper;
	public void execute(String memberNum, Model model, HttpSession session) {
		if(memberNum == null) {
			AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
			memberNum = memberMapper.getMemberNum(auth.getUserId());
		}
		List<CouponDetailDTO> couponDetailList = couponMapper.myCouponListSelect(memberNum);
		model.addAttribute("couponDetailList", couponDetailList);
	}
}
