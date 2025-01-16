package DDL.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.service.order.INIstdpayPcReturn;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("payment")
public class PayController {
	@Autowired
	INIstdpayPcReturn iNIstdpayPcReturn;
	
	@RequestMapping("INIstdpay_pc_return")
	public String payReturn(HttpServletRequest req, HttpSession session){
		iNIstdpayPcReturn.execute(req, session);
		return "redirect:/";
	}
	
	@RequestMapping("close")
	public String close() {
		return "thymeleaf/purchase/close";
	}
}