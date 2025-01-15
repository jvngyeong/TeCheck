package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.GoodsOrderDTO;
import DDL.domain.GoodsOrderDetailDTO;

@Mapper
public interface GoodsOrderMapper {
	void goodsOrderInsert(GoodsOrderDTO goodsOrderDTO);

	List<GoodsOrderDetailDTO> goodsOrderSelectList();

	GoodsOrderDetailDTO goodsOrderSelectOne(String goodsOrderNum);

	void goodsOrderUpdate(GoodsOrderDTO goodsOrderDTO);

	void goodsOrderDelete(String goodsOrderNum);
}
