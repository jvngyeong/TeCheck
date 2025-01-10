package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.ReviewCommand;
import DDL.service.review.GoodsReviewDetailService;
import DDL.service.review.GoodsReviewWriteService;
import DDL.service.review.ReviewListService;

@Controller
@RequestMapping("review")
public class ReviewController {
	@Autowired
	GoodsReviewDetailService goodsReviewDetailService;
	
	@Autowired
	GoodsReviewWriteService goodsReviewWriteService;
	
	@Autowired
	ReviewListService reviewListService;
	
	
	@GetMapping("goodsReview")
	public String goodsReview(ReviewCommand reviewCommand, Model model) {
		goodsReviewDetailService.execute(reviewCommand, model);
		return "thymeleaf/review/goodsReview";
	}
	
	@PostMapping("goodsReviewWrite")
	public String goodsReviewWrite(@Validated ReviewCommand reviewCommand, BindingResult result, Model model) {
		if(result.hasErrors()) {
			goodsReviewDetailService.execute(reviewCommand, model);
			return "thymeleaf/review/goodsReview";
		}
		goodsReviewWriteService.execute(reviewCommand);
		reviewListService.execute(model);
		return "thymeleaf/review/reviewList";
	}
	
	@GetMapping("reviewList")
	public String reviewList(Model model) {
		reviewListService.execute(model);
		return "thymeleaf/review/reviewList";
	}
}
