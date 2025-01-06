package DDL.service.help;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import DDL.command.FindCommand;
import DDL.mapper.HelpMapper;

@Service
public class PwChangeService {
	@Autowired
	HelpMapper helpMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public void execute(FindCommand findCommand) {
		findCommand.setUserPw(passwordEncoder.encode(findCommand.getUserPw()));
		helpMapper.pwChange(findCommand);
	}
}
