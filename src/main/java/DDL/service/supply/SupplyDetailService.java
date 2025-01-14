package DDL.service.supply;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.command.SupplyCommand;
import DDL.domain.SupplyContractDTO;
import DDL.mapper.SupplyMapper;

@Service
public class SupplyDetailService {
	@Autowired
	SupplyMapper supplyMapper;
	public void execute(String supNum, Model model) {
		SupplyContractDTO supplyContractDTO = supplyMapper.supplySelectOne(supNum);
		model.addAttribute("supplyContractDTO", supplyContractDTO);
		
		SupplyCommand supplyCommand = new SupplyCommand();
		BeanUtils.copyProperties(supplyContractDTO.getSupplyDTO(), supplyCommand);
		BeanUtils.copyProperties(supplyContractDTO.getContractDTO(), supplyCommand);
		model.addAttribute("supplyCommand", supplyCommand);
	}
}	
