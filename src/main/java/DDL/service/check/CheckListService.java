package DDL.service.check;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.CheckDTO;
import DDL.mapper.CheckMapper;

@Service
public class CheckListService {
	@Autowired
	CheckMapper checkMapper;
	public void execute(Model model) {
		List<CheckDTO> list = checkMapper.checkSelectList();
		model.addAttribute("list", list);
	}
}
