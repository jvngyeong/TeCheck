package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.service.chatting.ChattingRoomListService;
import DDL.service.chatting.ChattingServerService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("chatting")
public class ChattingController {
	@Autowired
	ChattingServerService chattingServerService;
	
	@Autowired
	ChattingRoomListService chattingRoomListService;
	
	@GetMapping("chatting")
	public String chatting(String roomNum, Model model) {
		model.addAttribute("roomNum", roomNum);
		return "thymeleaf/chatting/chatting";
	}
	
	@GetMapping("chattingList")
	public String chattingList(Model model, HttpSession session) {
		chattingRoomListService.execute(model, session);
		return "thymeleaf/chatting/chattingList";
	}
}
