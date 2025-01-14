package DDL.service.supply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.SupplyMapper;

@Service
public class SupplyDeleteService {
	@Autowired
	SupplyMapper supplyMapper;
	public void execute(String supNum) {
		supplyMapper.contractDelete(supNum);
		supplyMapper.supplyDelete(supNum);
	}
}
