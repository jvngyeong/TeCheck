package DDL.service.experience;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.ExperienceCommand;
import DDL.domain.ExperienceDTO;
import DDL.mapper.AutoNumMapper;
import DDL.mapper.ExperienceMapper;

@Service
public class ExperienceWriteService {
	@Autowired
	ExperienceMapper experienceMapper;
	@Autowired
    AutoNumMapper autoNumMapper;
	public void execute(ExperienceCommand experienceCommand) {
		ExperienceDTO dto = new ExperienceDTO();
		BeanUtils.copyProperties(experienceCommand, dto);
		String expNum = autoNumMapper.getAutoNum("exp_", "5", "exp_num", "experience");
		dto.setExpNum(expNum);
		
		experienceMapper.experienceInsert(dto);
		
	}
}
