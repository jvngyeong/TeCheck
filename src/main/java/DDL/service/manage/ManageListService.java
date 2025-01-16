package DDL.service.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.ManageDTO;
import DDL.mapper.ManageMapper;

@Service
public class ManageListService {
	@Autowired
	ManageMapper manageMapper;
	public void execute(Model model) {
		List<ManageDTO> list = manageMapper.manageSelectList();
		model.addAttribute("list", list);
	}
}
