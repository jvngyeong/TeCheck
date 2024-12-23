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
    public void execute(Model model) {
        List<GoodsDTO> list = goodsMapper.goodsListSelect();
        model.addAttribute("goodsList", list);
    }
}