package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.ReviewCommand;
import DDL.domain.AuthInfoDTO;
import DDL.mapper.MemberMapper;
import DDL.service.review.GoodsReviewDetailService;
import DDL.service.review.GoodsReviewWriteService;
import DDL.service.review.ReviewDeleteService;
import DDL.service.review.ReviewListService;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("review")
public class ReviewController {
	@Autowired
	GoodsReviewDetailService goodsReviewDetailService;
	
	@Autowired
	GoodsReviewWriteService goodsReviewWriteService;
	
	@Autowired
	ReviewListService reviewListService;
	
	@Autowired
	ReviewDeleteService reviewDeleteService;
	
	@GetMapping("goodsReview")
	public String goodsReview(ReviewCommand reviewCommand, Model model) {
		goodsReviewDetailService.execute(reviewCommand, model);
		return "thymeleaf/review/goodsReview";
	}
	
	@Autowired
	MemberMapper memberMapper;
	@PostMapping("goodsReviewWrite")
	public String goodsReviewWrite(@Validated ReviewCommand reviewCommand, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			goodsReviewDetailService.execute(reviewCommand, model);
			return "thymeleaf/review/goodsReview";
		}
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberNum = memberMapper.getMemberNum(auth.getUserId());
		
		goodsReviewWriteService.execute(reviewCommand);
		reviewListService.execute(null, memberNum, model);
		return "redirect:reviewList";
	}
	
	@GetMapping("reviewList")
	public String reviewList(Model model, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberNum = memberMapper.getMemberNum(auth.getUserId());
		reviewListService.execute(null, memberNum, model);
		return "thymeleaf/review/reviewList";
	}
	
	@GetMapping("reviewDelete")
	public String reviewDelete(String reviewNum) {
		reviewDeleteService.execute(reviewNum);
		return "redirect:reviewList";
	}
	
	@GetMapping("reviewEmpDelete")
	public String reviewEmpDelete(String reviewNum) {
		reviewDeleteService.execute(reviewNum);
		return "redirect:empReviewList";
	}
	
	@RequestMapping("reviewInfo")
	public String reviewInfo(@ModelAttribute("goodsNum") String goodsNum
			, Model model) {
		reviewListService.execute(goodsNum, null, model);
		return "thymeleaf/review/reviewInfo";
	}
	
	@GetMapping("empReviewList")
	public String empReviewList(Model model) {
		reviewListService.execute(null, null, model);
		return "thymeleaf/review/empReviewList";
	}
	
}
