package DDL.service.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.GoodsReviewDTO;
import DDL.mapper.ReviewMapper;

@Service
public class ReviewListService {
	@Autowired
	ReviewMapper reviewMapper;
	public void execute(Model model) {
		List<GoodsReviewDTO> list = reviewMapper.selectReviewList();
		model.addAttribute("list", list);
	}
}
