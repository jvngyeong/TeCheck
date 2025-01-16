package DDL.service.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.ManageMapper;

@Service
public class ManageDeleteService {
	@Autowired
	ManageMapper manageMapper;
	public void execute(String manageNum) {
		manageMapper.manageDelete(manageNum);
	}
}
