package DDL.service.store;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.StoreCommand;
import DDL.domain.StoreDTO;
import DDL.mapper.AutoNumMapper;
import DDL.mapper.StoreMapper;

@Service
public class StoreWriteService {
	@Autowired
	StoreMapper storeMapper;
	@Autowired
    AutoNumMapper autoNumMapper;
	public void execute(StoreCommand storeCommand) {
		StoreDTO dto = new StoreDTO();
		BeanUtils.copyProperties(storeCommand, dto);
		String storeNum = autoNumMapper.getAutoNum("store_", "7", "store_num", "store");
		dto.setStoreNum(storeNum);
		
		storeMapper.storeInsert(dto);
		
	}
}
