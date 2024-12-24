package DDL.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.domain.CartDTO;
import DDL.mapper.CartMapper;

@Service
public class CartMergeService {
	@Autowired
	CartMapper cartMapper;
	public void execute(String goodsNum, String memberNum, String cartQty) {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setGoodsNum(goodsNum);
		cartDTO.setMemberNum(memberNum);
		cartDTO.setCartQty(cartQty);
		cartMapper.cartMerge(cartDTO);
	}
}
