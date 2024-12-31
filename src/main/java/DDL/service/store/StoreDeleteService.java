package DDL.service.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.StoreMapper;

@Service
public class StoreDeleteService {
	@Autowired
	StoreMapper storeMapper;
	public void execute(String storeNum) {
		storeMapper.storeDelete(storeNum);
	}
}
