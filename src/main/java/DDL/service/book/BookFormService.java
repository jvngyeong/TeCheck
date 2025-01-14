package DDL.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.ExperienceDTO;
import DDL.mapper.ExperienceMapper;


@Service
public class BookFormService {
	@Autowired
	ExperienceMapper experienceMapper;
	public void execute(Model model) {
		List<ExperienceDTO> experienceList = experienceMapper.experienceSelectAll();
		model.addAttribute("experienceList", experienceList);
	}
}
