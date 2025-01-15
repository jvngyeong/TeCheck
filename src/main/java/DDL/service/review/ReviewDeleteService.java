package DDL.service.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.ReviewMapper;

@Service
public class ReviewDeleteService {
	@Autowired
	ReviewMapper reviewMapper;
	public void execute(String reviewNum) {
		reviewMapper.reviewDelete(reviewNum);
	}
}
