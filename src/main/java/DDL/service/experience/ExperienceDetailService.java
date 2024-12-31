package DDL.service.experience;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.ExperienceDTO;
import DDL.mapper.ExperienceMapper;

@Service
public class ExperienceDetailService {
	@Autowired
	ExperienceMapper experienceMapper;
	public void execute(String expNum, Model model) {
		ExperienceDTO dto = experienceMapper.experienceSelectOne(expNum);
		model.addAttribute("experienceCommand", dto);
	}

}
