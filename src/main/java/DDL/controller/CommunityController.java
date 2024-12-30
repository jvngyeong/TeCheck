package DDL.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DDL.command.CommunityCommand;
import DDL.domain.CommunityDTO;
import DDL.service.community.CommunityCommentService;
import DDL.service.community.CommunityDeleteService;
import DDL.service.community.CommunityDetailService;
import DDL.service.community.CommunityListService;
import DDL.service.community.CommunityUpdateService;
import DDL.service.community.CommunityWriteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("community")
public class CommunityController {
	@Autowired
	CommunityListService communityListService;
	@Autowired
	CommunityWriteService communityWriteService;
	@Autowired
	CommunityDetailService communityDetailService;
	@Autowired
	CommunityUpdateService communityUpdateService;
	@Autowired
	CommunityDeleteService communityDeleteService;
	@Autowired
	CommunityCommentService communityCommentService;
	@GetMapping("communityList")
	public String communityList(Model model, HttpSession session) {
		communityListService.execute(model, session);
		return "thymeleaf/community/communityList";
	}
	@GetMapping("communityWrite")
	public String communityWrite(Model model) {
		return "thymeleaf/community/communityWrite";
	}
	@PostMapping("communityWrite")
	public String communityWrite(CommunityCommand communityCommand, HttpSession session) {
		communityWriteService.execute(communityCommand, session);
		return "redirect:/community/communityList";
	}
	@GetMapping("communityDetail")
	public String communityDetail(String commNum, Model model) {
		communityDetailService.execute(commNum, model);
		return "thymeleaf/community/communityDetail";
	}
	@GetMapping("communityModify")
	public String communityModify(String commNum, Model model) {
		communityDetailService.execute(commNum, model);
		return "thymeleaf/community/communityModify";
	}
	@PostMapping("communityModify")
	public String communityModify(CommunityCommand communityCommand) {
		communityUpdateService.execute(communityCommand);
		return "redirect:/community/communityDetail?commNum="+communityCommand.getCommNum();
	}
	@GetMapping("communityDelete")
	public String communityDelete(String commNum) {
		communityDeleteService.execute(commNum);
		return "redirect:/community/communityList";
	}
	@PostMapping("/community/addComment")
    public String addComment(@RequestParam("commContents") String commContents, @RequestParam("commNum") int commNum) {
        // 댓글 저장
        communityCommentService.addComment(commContents, commNum);

        // 댓글 목록을 다시 가져와서 같은 페이지로 리다이렉트
        return "redirect:/community/communityDetail?commNum=" + commNum;
    }

}
