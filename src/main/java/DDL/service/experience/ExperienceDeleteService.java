package DDL.service.experience;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.ExperienceMapper;

@Service
public class ExperienceDeleteService {
	@Autowired
	ExperienceMapper experienceMapper;
	public void execute(String expNum) {
		experienceMapper.experienceDelete(expNum);
	}
}
