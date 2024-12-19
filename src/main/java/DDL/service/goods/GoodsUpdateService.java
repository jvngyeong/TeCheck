package DDL.service.goods;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.GoodsCommand;
import DDL.domain.GoodsDTO;
import DDL.mapper.GoodsMapper;

@Service
public class GoodsUpdateService {
    @Autowired
    GoodsMapper goodsMapper;
    public void execute(GoodsCommand goodsCommand) {
    	GoodsDTO goodsDTO = new GoodsDTO();
        BeanUtils.copyProperties(goodsCommand, goodsDTO);
        goodsMapper.goodsUpdate(goodsDTO);
    }
}