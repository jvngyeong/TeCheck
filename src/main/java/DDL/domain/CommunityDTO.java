package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("communityDTO")
public class CommunityDTO {
	String rownum;
	String commNum;
	String memberNum;
	String memberId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date commDate;
	String commTitle;
	String commContents;
	
	
}
