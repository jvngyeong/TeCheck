package DDL.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.OrderDTO;
import DDL.mapper.OrderMapper;

@Service
public class OrderListService {
	@Autowired
	OrderMapper orderMapper;
	public void execute(String memberNum, Model model) {
		List<OrderDTO> list = orderMapper.orderSelectByMemberNum(memberNum);
		model.addAttribute("list", list);
	}
}
