package DDL.service.store;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.StoreCommand;
import DDL.domain.StoreDTO;
import DDL.mapper.StoreMapper;

@Service
public class StoreUpdateService {
	@Autowired
	StoreMapper storeMapper;
	public void execute(StoreCommand storeCommand) {
		StoreDTO dto = new StoreDTO();
		BeanUtils.copyProperties(storeCommand, dto);
		
		storeMapper.storeUpdate(dto);
	}
}
