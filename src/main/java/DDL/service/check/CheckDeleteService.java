package DDL.service.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.CheckMapper;

@Service
public class CheckDeleteService {
	@Autowired
	CheckMapper checkMapper;
	public void execute(String checkNum) {
		checkMapper.checkDelete(checkNum);
	}
}
