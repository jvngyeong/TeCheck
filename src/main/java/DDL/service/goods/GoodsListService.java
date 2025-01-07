package DDL.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.GoodsDTO;
import DDL.mapper.GoodsMapper;


@Service
public class GoodsListService {
	@Autowired
	GoodsMapper goodsMapper;
	
	public void execute(String searchWord, Model model) {
		List<GoodsDTO> list = goodsMapper.goodsListSelect(searchWord);
		if(searchWord == null) searchWord="";
		model.addAttribute("searchWord", searchWord);
        model.addAttribute("goodsList", list);
	}
}
