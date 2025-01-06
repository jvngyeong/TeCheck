package DDL.service.help;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.FindCommand;
import DDL.mapper.HelpMapper;

@Service
public class UserCheckService {
	@Autowired
	HelpMapper helpMapper;
	public String execute(FindCommand findCommand) {
		String grade = null;
		if(findCommand != null) {
			grade = helpMapper.userCheck(findCommand);
		}
		return grade;
	}
}
