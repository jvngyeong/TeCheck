package DDL.service.supply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.SupplyContractDTO;
import DDL.mapper.SupplyMapper;

@Service
public class SupplyListService {
	@Autowired
	SupplyMapper supplyMapper;
	public void execute(Model model) {
		List<SupplyContractDTO> supplyList = supplyMapper.supplyListSelect();
		model.addAttribute("supplyList", supplyList);
	}
}
