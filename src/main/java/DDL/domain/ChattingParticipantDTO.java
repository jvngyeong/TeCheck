package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("chattingParticipantDTO")
public class ChattingParticipantDTO {
	String roomNum;
	String participantNum;
	Date joinDate;
}
