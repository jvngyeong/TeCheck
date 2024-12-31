package DDL.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.OrderPurchaseListGoodsDTO;
import DDL.mapper.OrderMapper;

@Service
public class OrderDetailService {
	@Autowired
	OrderMapper orderMapper;
	public void execute(String orderNum, Model model) {
		List<OrderPurchaseListGoodsDTO> list = orderMapper.orderPurchaseListSelectOne(orderNum);
		model.addAttribute("list", list);
	}
}
