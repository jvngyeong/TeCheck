package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.GoodsDTO;
import DDL.domain.StartEndPageDTO;
@Mapper
public interface GoodsMapper {

    Integer goodsWrite(GoodsDTO goodsDTO);

    GoodsDTO goodsSelectOne(String goodsNum);

    Integer goodsUpdate(GoodsDTO goodsDTO);

    Integer goodsDelete(String goodsNum);

	Integer goodsVisitCountUpdate(String goodsNum);

	

	int goodsCount(String searchWord);

	List<GoodsDTO> goodsListSelect(StartEndPageDTO sepDTO);

	List<GoodsDTO> goodsCategory();

	List<GoodsDTO> goodsAllSelect();

	List<GoodsDTO> goodsSortedListSelect(StartEndPageDTO sepDTO);

	
}