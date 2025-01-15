package DDL.service.goodsOrder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.GoodsOrderDetailDTO;
import DDL.mapper.GoodsOrderMapper;

@Service
public class GoodsOrderListService {
	@Autowired
	GoodsOrderMapper goodsOrderMapper;
	
	public void execute(Model model) {
		List<GoodsOrderDetailDTO> goodsOrderDetailList = goodsOrderMapper.goodsOrderSelectList();
		model.addAttribute("goodsOrderDetailList", goodsOrderDetailList);
	}
}
