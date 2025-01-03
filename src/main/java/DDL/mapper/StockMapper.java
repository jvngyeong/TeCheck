package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.StockDTO;

@Mapper
public interface StockMapper {
	List<StockDTO> getStockData();
}
