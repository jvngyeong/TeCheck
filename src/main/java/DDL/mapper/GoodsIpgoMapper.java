package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import DDL.domain.GoodsIpgoDTO;
import DDL.domain.GoodsIpgoNameDTO;

@Mapper
public interface GoodsIpgoMapper {
	public List<GoodsIpgoDTO> goodsIpgoList();

	public Integer goodsIpgo(GoodsIpgoDTO dto);

	public GoodsIpgoNameDTO ipgoSelectOne(
			@Param(value = "ipgoNum") String ipgoNum,
			@Param(value = "goodsNum" ) String goodsNum);
	
}
