package DDL.service.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.ManageDTO;
import DDL.mapper.ManageMapper;

@Service
public class ManageSelectService {
	@Autowired
	ManageMapper manageMapper;
	public void execute(String manageNum, Model model) {
		ManageDTO dto = manageMapper.manageSelectOne(manageNum);
		model.addAttribute("manageCommand", dto);
	}
}
