package DDL.service.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.GoodsReviewDTO;
import DDL.domain.ReviewDTO;
import DDL.mapper.ReviewMapper;

@Service
public class ReviewListService {
	@Autowired
	ReviewMapper reviewMapper;
	public void execute(String goodsNum, String memberNum, Model model) {
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("goodsNum", goodsNum);
		map.put("memberNum", memberNum);
		List<GoodsReviewDTO> list = reviewMapper.selectReviewList(map);
		
		model.addAttribute("list", list);
	}
}
