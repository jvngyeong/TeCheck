package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.CouponCommand;
import DDL.command.MemberCouponCommand;
import DDL.service.coupon.CouponDeleteService;
import DDL.service.coupon.CouponDetailService;
import DDL.service.coupon.CouponListService;
import DDL.service.coupon.CouponUpdateService;
import DDL.service.coupon.CouponWriteService;
import DDL.service.coupon.MemberCouponWriteService;
import DDL.service.members.MemberListService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("coupon")
public class CouponController {
	@Autowired
	CouponWriteService couponWriteService;
	
	@Autowired
	CouponListService couponListService;
	
	@Autowired
	CouponDetailService couponDetailService;
	
	@Autowired
	CouponUpdateService couponUpdateService;
	
	@Autowired
	CouponDeleteService couponDeleteService;
	
	@Autowired
	MemberListService memberListService;
	
	@Autowired
	MemberCouponWriteService memberCouponWriteService;
	
	@GetMapping("couponList")
	public String couponList(Model model) {
		couponListService.execute(model);
		return "thymeleaf/coupon/couponList";
	}
	
	@GetMapping("couponWrite")
	public String couponWrite(Model model) {
		CouponCommand couponCommand = new CouponCommand();
		model.addAttribute("couponCommand", couponCommand);
		return "thymeleaf/coupon/couponWrite";
	}
	
	@PostMapping("couponWrite")
	public String couponWrite(@Validated CouponCommand couponCommand, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("couponCommand", couponCommand);
			return "thymeleaf/coupon/couponWrite";
		}
		couponWriteService.execute(couponCommand);
		return "redirect:/coupon/couponList";
	}
	
	@GetMapping("couponUpdate")
	public String couponUpdate(String couponNum, Model model) {
		couponDetailService.execute(couponNum, model);
		return "thymeleaf/coupon/couponUpdate";
	}
	
	@PostMapping("couponUpdate")
	public String couponUpdate(@Validated CouponCommand couponCommand, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("couponCommand", couponCommand);
			return "thymeleaf/coupon/couponUpdate";
		}
		couponUpdateService.execute(couponCommand);
		return "redirect:/coupon/couponList";
	}
	
	@GetMapping("couponDelete")
	public String couponDelete(String couponNum) {
		couponDeleteService.execute(couponNum);
		return "redirect:/coupon/couponList";
	}
	
	@GetMapping("memberCouponList")
	public String memberCouponList() {
		return "thymeleaf/coupon/memberCouponList";
	}
	
	@GetMapping("memberCouponWrite")
	public String memberCouponWrite(Model model) {
		couponListService.execute(model);
		MemberCouponCommand memberCouponCommand = new MemberCouponCommand();
		model.addAttribute("memberCouponCommand", memberCouponCommand);
		return "thymeleaf/coupon/memberCouponWrite";
	}
	
	@GetMapping("couponSearch")
	public String couponSearch(Model model) {
		couponListService.execute(model);
		return "thymeleaf/coupon/couponSearch";
	}
	
	@GetMapping("memberSearch")
	public String memberSearch(Model model) {
		memberListService.execute(model);
		return "thymeleaf/coupon/memberSearch";
	}
	
	@PostMapping("memberCouponWrite")
	public String memberCouponWrite(@Validated MemberCouponCommand memberCouponCommand, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("memberCouponCommand", memberCouponCommand);
			return "thymeleaf/coupon/memberCouponWrite";
		}
		memberCouponWriteService.execute(memberCouponCommand, session);
		return "redirect:/coupon/memberCouponList";
	}
}
