package DDL.service.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.CheckDTO;
import DDL.mapper.CheckMapper;

@Service
public class CheckSelectService {
	@Autowired
	CheckMapper checkMapper;
	public void execute(String checkNum, Model model) {
		CheckDTO dto = checkMapper.checkSelectOne(checkNum);
		model.addAttribute("checkCommand", dto);
	}
}
