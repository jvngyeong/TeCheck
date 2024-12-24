package DDL.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.CartMapper;

@Service
public class CartUpdateService {
	@Autowired
	CartMapper cartMapper;
	public void execute(String cartQty, String goodsNum, String memberNum) {
		cartMapper.cartUpdate(cartQty, goodsNum, memberNum);
	}
}
