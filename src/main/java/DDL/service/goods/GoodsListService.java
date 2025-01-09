package DDL.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.GoodsDTO;
import DDL.domain.StartEndPageDTO;
import DDL.mapper.GoodsMapper;
import DDL.service.StartEndPageService;


@Service
public class GoodsListService {
	@Autowired
	GoodsMapper goodsMapper;
	@Autowired
	StartEndPageService startEndPageService;
	public void execute(String searchWord, Model model, int page) {
		if(searchWord == null && page < 0) {
			List<GoodsDTO> list = goodsMapper.goodsAllSelect();
			model.addAttribute("list", list);
		}else {
			// 한페이지에 보일 list
			int limit = 6;
			StartEndPageDTO sepDTO = startEndPageService.execute(page, limit, searchWord);
			
			
			List<GoodsDTO> list = goodsMapper.goodsListSelect(sepDTO);
			int count = goodsMapper.goodsCount(searchWord);
			// 페이징
			startEndPageService.execute(page, limit, count, searchWord, list, model);
		}
	}
}
