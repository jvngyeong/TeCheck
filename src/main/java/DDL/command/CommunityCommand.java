package DDL.command;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class CommunityCommand {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	String commNum;
	String commTitle;
	String commContents;
}
