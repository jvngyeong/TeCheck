package DDL.service.wish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.WishMapper;

@Service
public class WishMergeService {
	@Autowired
	WishMapper wishMapper;
	
	public void execute(String goodsNum, String memberNum) {
		wishMapper.wishMerge(goodsNum, memberNum);
	}
}
