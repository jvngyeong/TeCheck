package DDL.service.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.command.ReviewCommand;
import DDL.domain.ReviewDTO;
import DDL.mapper.ReviewMapper;
import DDL.service.goods.GoodsDetailService;

@Service
public class GoodsReviewDetailService {
	@Autowired
	GoodsDetailService goodsDetailService;
	
	@Autowired
	ReviewMapper reviewMapper;
	public void execute(ReviewCommand reviewCommand, Model model) {
		ReviewDTO reviewDTO = reviewMapper.reviewSelectOne(reviewCommand.getOrderNum(), reviewCommand.getGoodsNum());
		if(reviewDTO != null) {
			reviewCommand.setReviewContent(reviewDTO.getReviewContent());
			reviewCommand.setReviewNum(reviewDTO.getReviewNum());
		}
		goodsDetailService.execute(reviewCommand.getGoodsNum(), model);
		model.addAttribute("reviewCommand", reviewCommand);
	}
}
