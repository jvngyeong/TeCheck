package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.ExperienceDTO;

@Mapper
public interface ExperienceMapper {
	public Integer experienceInsert(ExperienceDTO dto);
	public List<ExperienceDTO> experienceSelectAll();
	public ExperienceDTO experienceSelectOne(String expNum);
	public Integer experienceUpdate(ExperienceDTO dto);
	public Integer experienceDelete(String expNum);
}
