package DDL.service.goodsIpgo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.GoodsIpgoDTO;
import DDL.mapper.GoodsIpgoMapper;
@Service
public class GoodsIpgoListService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	public void execute(Model model) {
		List<GoodsIpgoDTO> list = goodsIpgoMapper.goodsIpgoList();
		model.addAttribute("list", list);
	}
}
