package DDL.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class CommunityReplyCommand {
	String commNum;
	String commTitle;
	String commContents;
	
	String replyNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date replyDate;
	String replyContent;
	String memberNum;
	String memberId;
}
