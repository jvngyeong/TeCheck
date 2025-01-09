package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("chattingRoomDTO")
public class ChattingRoomDTO {
	String roomNum;
	Date roomCreated;
	Date roomUpdated;
}
