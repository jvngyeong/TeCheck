package DDL.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("experienceDTO")
public class ExperienceDTO {
	String expNum;
	String expContents;
	String expName;
}
