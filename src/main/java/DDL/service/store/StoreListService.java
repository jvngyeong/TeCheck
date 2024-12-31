package DDL.service.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.StoreDTO;
import DDL.mapper.StoreMapper;

@Service
public class StoreListService {
	@Autowired
	StoreMapper storeMapper;
	public void execute(Model model) {
		List<StoreDTO> list = storeMapper.storeSelectAll();
		model.addAttribute("list", list);
	}
}
