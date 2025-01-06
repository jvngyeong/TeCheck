package DDL.service.help;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.command.FindCommand;
import DDL.mapper.HelpMapper;

@Service
public class IdFindService {
	@Autowired
	HelpMapper helpMapper;
	public void execute(FindCommand findIdCommand, Model model) {
		String userId = helpMapper.findId(findIdCommand);
		findIdCommand.setUserId(userId);
		model.addAttribute("findIdCommand", findIdCommand);
	}
}
