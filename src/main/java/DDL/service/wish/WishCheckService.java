package DDL.service.wish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.WishMapper;

@Service
public class WishCheckService {
	@Autowired
	WishMapper wishMapper;
	public String execute(String goodsNum, String memberNum) {
		return wishMapper.wishCheck(goodsNum, memberNum);
	}
}
