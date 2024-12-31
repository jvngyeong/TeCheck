package DDL.service.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.StoreDTO;
import DDL.mapper.StoreMapper;

@Service
public class StoreDetailService {
	@Autowired
	StoreMapper storeMapper;
	public void execute(String storeNum, Model model) {
		StoreDTO dto = storeMapper.storeSelectOne(storeNum);
		model.addAttribute("storeCommand", dto);
	}

}
