package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("communityDTO")
public class CommunityDTO {
	String communityNum;
	String memberNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date communityDate;
	String communityTitle;
	String communityContents;
}
