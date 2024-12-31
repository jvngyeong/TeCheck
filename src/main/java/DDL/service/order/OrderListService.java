package DDL.service.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.OrderPurchaseListGoodsDTO;
import DDL.mapper.OrderMapper;

@Service
public class OrderListService {
	@Autowired
	OrderMapper orderMapper;
	public void execute(String memberNum, Model model) {
		List<OrderPurchaseListGoodsDTO> list = orderMapper.orderSelectByMemberNum(memberNum);
		Map<String, List<OrderPurchaseListGoodsDTO>> groupedByOrderNum = new HashMap<>();
		for (OrderPurchaseListGoodsDTO dto : list) {
		    if (!groupedByOrderNum.containsKey(dto.getOrderDTO().getOrderNum())) {
		        groupedByOrderNum.put(dto.getOrderDTO().getOrderNum(), new ArrayList<>());
		    }
		    groupedByOrderNum.get(dto.getOrderDTO().getOrderNum()).add(dto);
		}
		List<List<OrderPurchaseListGoodsDTO>> splitList = new ArrayList<>(groupedByOrderNum.values());
		model.addAttribute("splitList", splitList);
	}
}
