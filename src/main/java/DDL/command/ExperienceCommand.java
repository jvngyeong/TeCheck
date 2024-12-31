package DDL.command;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ExperienceCommand {
	String expNum;
	@NotEmpty(message = "체험내용을 입력해주세요")
	String expContents;
	@NotEmpty(message = "체험이름을 입력해주세요")
	String expName;
}
