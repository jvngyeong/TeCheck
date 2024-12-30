package DDL.service.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.OrderCommand;
import DDL.domain.AuthInfoDTO;
import DDL.domain.GoodsDTO;
import DDL.domain.MemberDTO;
import DDL.domain.OrderDTO;
import DDL.mapper.GoodsMapper;
import DDL.mapper.MemberMapper;
import DDL.mapper.OrderMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class OrderService {
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	GoodsMapper goodsMapper;
	public String execute(OrderCommand orderCommand, String[] cartQties, HttpSession session) {
		String orderNum = orderMapper.getOrderNum();
		for(String gnum : orderCommand.getGoodsNums()) {
			System.out.println("goodsNum = " + gnum);
		}
		OrderDTO dto = new OrderDTO();
		BeanUtils.copyProperties(orderCommand, dto);
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberNum = memberMapper.getMemberNum(auth.getUserId());
		MemberDTO memDTO = memberMapper.memberSelectOne(memberNum);
		dto.setMemberNum(memberNum);
		dto.setOrderNum(orderNum);
		
		orderMapper.orderInsert(dto);
		
		//구매 리스트
		String goodsNums[] = orderCommand.getGoodsNums();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNum", orderNum);
		map.put("memberNum", memberNum);
		map.put("goodsNums", goodsNums);
		map.put("cartQties", cartQties);
		orderMapper.purchaseListInsert(map);
		
		GoodsDTO goodsDTO = goodsMapper.goodsSelectOne(goodsNums[0]);
		session.setAttribute("buyerName", memDTO.getMemberName());
		session.setAttribute("buyerPhone", memDTO.getMemberPhone());
		session.setAttribute("goodsName", goodsDTO.getGoodsName() + " 외 " + (goodsNums.length - 1) + "개");
		return orderNum;
	}
}
