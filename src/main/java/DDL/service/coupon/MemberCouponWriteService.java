package DDL.service.coupon;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.MemberCouponCommand;
import DDL.domain.AuthInfoDTO;
import DDL.domain.MemberCouponDTO;
import DDL.mapper.CouponMapper;
import DDL.mapper.EmployeeMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberCouponWriteService {
	@Autowired
	CouponMapper couponMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	public void execute(MemberCouponCommand memberCouponCommand, HttpSession session) {
		MemberCouponDTO memberCouponDTO = new MemberCouponDTO();
		BeanUtils.copyProperties(memberCouponCommand, memberCouponDTO);
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String empNum = employeeMapper.getEmpNum(auth.getUserId());
		memberCouponDTO.setEmpNum(empNum);
		couponMapper.memberCouponWrite(memberCouponDTO);
	}
}
