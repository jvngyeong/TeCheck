package DDL.mapper;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.GoodsOrderDTO;

@Mapper
public interface GoodsOrderMapper {
	void goodsOrderInsert(GoodsOrderDTO goodsOrderDTO);
}
