package DDL.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class CommunityCommand {
	String communityNum;
	String memberNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date communityDate;
	String communityTitle;
	String communityContents;
}
