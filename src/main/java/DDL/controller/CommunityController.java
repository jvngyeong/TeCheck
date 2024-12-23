package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.CommunityCommand;
import DDL.service.community.CommunityListService;
import DDL.service.community.CommunityWriteService;

@Controller
@RequestMapping("community")
public class CommunityController {
	@Autowired
	CommunityListService communityListService;
	@Autowired
	CommunityWriteService communityWriteService;
	@GetMapping("communityList")
	public String communitylist(Model model) {
		communityListService.execute(model);
		return "thymeleaf/community/communityList";
	}
	@GetMapping("communityWrite")
	public String communitywrite() {
		return "thymeleaf/community/communityWrite";
	}
	@PostMapping("communityWrite")
	public String communitywrite(Model model, CommunityCommand communityCommand) {
		communityWriteService.execute(communityCommand);
		return "redirect:/community/communityList";
	}
}
