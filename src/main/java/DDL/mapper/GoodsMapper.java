package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.GoodsDTO;
@Mapper
public interface GoodsMapper {

    void goodsWrite(GoodsDTO goodsDTO);

    List<GoodsDTO> goodsListSelect();

    GoodsDTO goodsSelectOne(String goodsNum);

    void goodsUpdate(GoodsDTO goodsDTO);

    void goodsDelete(String goodsNum);
}