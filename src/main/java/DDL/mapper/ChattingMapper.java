package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.ChattingParticipantDTO;
import DDL.domain.ChattingRoomDTO;
import DDL.domain.MessageDTO;

@Mapper
public interface ChattingMapper {
	List<ChattingParticipantDTO> getChattingParticipantInfo(String userNum);

	void createChattingRoom(String roomNum);

	void addUser(String roomNum, String userNum);

	List<MessageDTO> getMessageList(String roomNum);

	void saveMessage(String messageNum, String roomNum, String userNum, String receivedMessage);

	List<ChattingRoomDTO> getChattingRoomList();
}
