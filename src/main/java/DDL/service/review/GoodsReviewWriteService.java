package DDL.service.review;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.ReviewCommand;
import DDL.domain.ReviewDTO;
import DDL.mapper.AutoNumMapper;
import DDL.mapper.ReviewMapper;

@Service
public class GoodsReviewWriteService {
	@Autowired
	AutoNumMapper autoNumMapper;
	
	@Autowired
	ReviewMapper reviewMapper;
	public void execute(ReviewCommand reviewCommand) {
		if(reviewCommand.getReviewNum() == null || reviewCommand.getReviewNum().equals("")) {
			String reviewNum = autoNumMapper.getAutoNum("review_", "8", "review_num", "review");
			reviewCommand.setReviewNum(reviewNum);
		}
		ReviewDTO reviewDTO = new ReviewDTO();
		BeanUtils.copyProperties(reviewCommand, reviewDTO);
		reviewDTO.setBookNum("");
		reviewMapper.reviewWrite(reviewDTO);
	}
}
