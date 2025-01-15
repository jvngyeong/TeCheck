package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
@Alias("communityReplyDTO")
public class CommunityReplyDTO {
	String replyNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date replyDate;	
	String replyContent;	
	String memberNum;
	String commNum;
	String empNum;
}