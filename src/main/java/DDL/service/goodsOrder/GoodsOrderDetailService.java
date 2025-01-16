package DDL.service.goodsOrder;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.command.GoodsOrderCommand;
import DDL.domain.GoodsOrderDetailDTO;
import DDL.mapper.GoodsOrderMapper;

@Service
public class GoodsOrderDetailService {
	@Autowired
	GoodsOrderMapper goodsOrderMapper;
	
	public void execute(String goodsOrderNum, Model model) {
		GoodsOrderDetailDTO goodsOrderDetailDTO = goodsOrderMapper.goodsOrderSelectOne(goodsOrderNum);
		GoodsOrderCommand goodsOrderCommand = new GoodsOrderCommand();
		BeanUtils.copyProperties(goodsOrderDetailDTO.getGoodsOrderDTO(), goodsOrderCommand);
		BeanUtils.copyProperties(goodsOrderDetailDTO.getGoodsDTO(), goodsOrderCommand);
		model.addAttribute("goodsOrderCommand", goodsOrderCommand);
	}
}
