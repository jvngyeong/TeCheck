package DDL.service.supply;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.SupplyCommand;
import DDL.domain.ContractDTO;
import DDL.domain.SupplyDTO;
import DDL.mapper.SupplyMapper;

@Service
public class SupplyUpdateService {
	@Autowired
	SupplyMapper supplyMapper;
	public void execute(SupplyCommand supplyCommand) {
		SupplyDTO supplyDTO = new SupplyDTO();
		ContractDTO contractDTO = new ContractDTO();
		
		BeanUtils.copyProperties(supplyCommand, supplyDTO);
		BeanUtils.copyProperties(supplyCommand, contractDTO);
		String contractStart = supplyCommand.getContractStart();
		String contractEnd = supplyCommand.getContractEnd();
		contractDTO.setContractPeriod(contractStart + " - " + contractEnd);
		supplyMapper.supplyUpdate(supplyDTO);
		supplyMapper.contractUpdate(contractDTO);
	}
}
