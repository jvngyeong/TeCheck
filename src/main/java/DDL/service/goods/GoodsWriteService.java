package DDL.service.goods;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.GoodsCommand;
import DDL.domain.GoodsDTO;
import DDL.mapper.GoodsMapper;

@Service
public class GoodsWriteService {
    @Autowired
    GoodsMapper goodsMapper;
    public void execute(GoodsCommand goodsCommand) {
        GoodsDTO goodsDTO = new GoodsDTO();
        BeanUtils.copyProperties(goodsCommand, goodsDTO);
        goodsDTO.setGoodsNum("goods_100001");
        goodsDTO.setGoodsName("a");
        goodsDTO.setGoodsPrice(11);
        goodsDTO.setGoodsContents("11");
        goodsDTO.setVisitCount(11);
        goodsDTO.setGoodsMainImage("11");
        goodsDTO.setGoodsDetailImage("11");
        goodsDTO.setGoodsMainStoreImage("11");
        goodsDTO.setGoodsDetailStoreImage("11");
        goodsDTO.setGoodsRegist("");
        goodsDTO.setGoodsUpdateDate("");
        goodsMapper.goodsWrite(goodsDTO);
    }
}