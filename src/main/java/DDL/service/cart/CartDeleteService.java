package DDL.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.CartMapper;

@Service
public class CartDeleteService {
	@Autowired
	CartMapper cartMapper;
	public String execute(String goodsNum, String memberNum) {
		int i = cartMapper.cartDelete(goodsNum, memberNum);
		if(i > 0) {
			return "200";
		}
		return "400";
	}
}
