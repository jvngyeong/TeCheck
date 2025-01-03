package DDL.service.stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.StockDTO;
import DDL.mapper.StockMapper;

@Service
public class StockDataService {
	@Autowired
	StockMapper stockMapper;
	public void execute(Model model) {
		List<StockDTO> list = stockMapper.getStockData();
		model.addAttribute("stockList", list);
		List<StockDTO> reversedList = new ArrayList<>(list);
		Collections.reverse(reversedList);
		model.addAttribute("reversedList", reversedList);
	}
}
