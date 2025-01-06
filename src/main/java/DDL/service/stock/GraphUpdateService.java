package DDL.service.stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.domain.StockDTO;
import DDL.mapper.StockMapper;

@Service
public class GraphUpdateService {
	@Autowired
	StockMapper stockMapper;
	public StockDTO execute() {
		List<StockDTO> list = stockMapper.getStockData();
		List<StockDTO> reversedList = new ArrayList<>(list);
		Collections.reverse(reversedList);
		StockDTO todayStock = reversedList.get(0);
		return todayStock;
	}
}
