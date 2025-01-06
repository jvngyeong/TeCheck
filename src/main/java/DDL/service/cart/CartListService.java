package DDL.service.cart;

import java.util.ArrayList;
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
		List<GoodsCartDTO> goodsCartDTOList = new ArrayList<GoodsCartDTO>();
		if(auth != null) {
			String memberNum = memberMapper.getMemberNum(auth.getUserId());
			if(memberNum != null) {
				goodsCartDTOList = cartMapper.cartSelect(memberNum);
				int totalPrice = 0;
				for(GoodsCartDTO gcDTO : goodsCartDTOList) {
					totalPrice += gcDTO.getGoodsDTO().getGoodsPrice() * Integer.parseInt(gcDTO.getCartDTO().getCartQty());
				}
				model.addAttribute("totalPrice", totalPrice);
				session.setAttribute("totalCartQty", goodsCartDTOList.size());
			}
		}
		model.addAttribute("goodsCartDTOList", goodsCartDTOList);
	}
}
