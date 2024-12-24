package DDL.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.AuthInfoDTO;
import DDL.domain.GoodsCartDTO;
import DDL.mapper.CartMapper;
import DDL.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class CartListService {
	@Autowired
	CartMapper cartMapper;
	
	@Autowired
	MemberMapper memberMapper;
	public void execute(Model model, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberNum = memberMapper.getMemberNum(auth.getUserId());
		List<GoodsCartDTO> goodsCartDTOList = cartMapper.cartSelect(memberNum);
		model.addAttribute("goodsCartDTOList", goodsCartDTOList);
		int totalPrice = 0;
		for(GoodsCartDTO gcDTO : goodsCartDTOList) {
			totalPrice += gcDTO.getGoodsDTO().getGoodsPrice() * Integer.parseInt(gcDTO.getCartDTO().getCartQty());
		}
		model.addAttribute("totalPrice", totalPrice);
	}
}
