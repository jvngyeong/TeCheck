package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("messageDTO")
public class MessageDTO {
	String messageNum;
	String roomNum;
	String participantNum;
	String userId;
	String message;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	Date messageDate;
}
