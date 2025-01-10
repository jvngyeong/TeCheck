package DDL.domain;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("chattingRoomParticipantDTO")
public class ChattingRoomParticipantDTO {
	ChattingRoomDTO chattingRoomDTO;
	ChattingParticipantDTO chattingParticipantDTO;
	MemberDTO memberDTO;
	MessageDTO messageDTO;
}
