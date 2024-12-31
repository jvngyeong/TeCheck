package DDL.service.experience;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.ExperienceCommand;
import DDL.domain.ExperienceDTO;
import DDL.mapper.ExperienceMapper;

@Service
public class ExperienceUpdateService {
	@Autowired
	ExperienceMapper experienceMapper;
	public void execute(ExperienceCommand experienceCommand) {
		ExperienceDTO dto = new ExperienceDTO();
		BeanUtils.copyProperties(experienceCommand, dto);
		
		experienceMapper.experienceUpdate(dto);
	}
}
