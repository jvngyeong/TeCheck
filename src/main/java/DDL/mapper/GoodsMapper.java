package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.GoodsDTO;
@Mapper
public interface GoodsMapper {

    Integer goodsWrite(GoodsDTO goodsDTO);

    List<GoodsDTO> goodsListSelect(String searchWord);

    GoodsDTO goodsSelectOne(String goodsNum);

    Integer goodsUpdate(GoodsDTO goodsDTO);

    Integer goodsDelete(String goodsNum);

	Integer goodsVisitCountUpdate(String goodsNum);

	List<GoodsDTO> goodsCategories();

	
}