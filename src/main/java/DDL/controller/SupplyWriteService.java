package DDL.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.SupplyCommand;
import DDL.domain.ContractDTO;
import DDL.domain.SupplyDTO;
import DDL.mapper.AutoNumMapper;
import DDL.mapper.SupplyMapper;

@Service
public class SupplyWriteService {
	@Autowired
	SupplyMapper supplyMapper;
	
	@Autowired
	AutoNumMapper autoNumMapper;
	public void execute(SupplyCommand supplyCommand) {
		SupplyDTO supplyDTO = new SupplyDTO();
		ContractDTO contractDTO = new ContractDTO();
		BeanUtils.copyProperties(supplyCommand, supplyDTO);
		String supNum = autoNumMapper.getAutoNum("sup_", "5", "sup_num", "supplier");
		supplyDTO.setSupNum(supNum);
		BeanUtils.copyProperties(supplyCommand, contractDTO);
		String conNum = autoNumMapper.getAutoNum("con_", "5", "contract_num", "contract");
		contractDTO.setContractNum(conNum);
		String contractStart = supplyCommand.getContractStart();
		String contractEnd = supplyCommand.getContractEnd();
		contractDTO.setContractPeriod(contractStart + " - " + contractEnd);
		contractDTO.setSupNum(supNum);
		supplyMapper.supplyInsert(supplyDTO);
		supplyMapper.contractInsert(contractDTO);
	}
}
