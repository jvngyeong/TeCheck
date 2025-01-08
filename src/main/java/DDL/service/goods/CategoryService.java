package DDL.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.GoodsDTO;
import DDL.mapper.GoodsMapper;

@Service
public class CategoryService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(Model model) {
		List<GoodsDTO> list = goodsMapper.goodsCategory();
		model.addAttribute("categoryList", list);
		int totalCount = 0;
		for(GoodsDTO dto : list) {
			totalCount += dto.getGoodsQty();
		}
		model.addAttribute("totalCount", totalCount);
	}
}
