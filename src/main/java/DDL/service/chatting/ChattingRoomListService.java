package DDL.service.chatting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.ChattingRoomParticipantDTO;
import DDL.mapper.ChattingMapper;
import DDL.mapper.EmployeeMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class ChattingRoomListService {
	@Autowired
	ChattingMapper chattingMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(Model model, HttpSession session) {
		List <ChattingRoomParticipantDTO> chattingRoomList = chattingMapper.getChattingRoomList();
		model.addAttribute("chattingRoomList", chattingRoomList);
	}
}
