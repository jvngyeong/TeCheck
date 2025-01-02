package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.ExperienceCommand;
import DDL.service.experience.ExperienceDeleteService;
import DDL.service.experience.ExperienceDetailService;
import DDL.service.experience.ExperienceListService;
import DDL.service.experience.ExperienceUpdateService;
import DDL.service.experience.ExperienceWriteService;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("experience")
public class ExperienceController {
	@Autowired
	ExperienceWriteService experienceWriteService;
	@Autowired
	ExperienceListService experienceListService;
	@Autowired
	ExperienceDetailService experienceDetailService;
	@Autowired
	ExperienceUpdateService experienceUpdateService;
	@Autowired
	ExperienceDeleteService experienceDeleteService;
	@GetMapping("experienceList")
	public String experienceList(Model model) {
		experienceListService.execute(model);
		return "thymeleaf/experience/experienceList";
	}
	@GetMapping("experienceWrite")
	public String experienceWrite(Model model) {
		ExperienceCommand experienceCommand = new ExperienceCommand();
		model.addAttribute("experienceCommand", experienceCommand);
		return "thymeleaf/experience/experienceWrite";
	}
	@PostMapping("experienceWrite")
	public String experienceWrite(@Validated ExperienceCommand experienceCommand
			, BindingResult result) {
		if (result.hasErrors()) {
		    result.getAllErrors().forEach(error -> {
		        System.out.println("Error: " + error.getDefaultMessage());
		    });
		    return "thymeleaf/experience/experienceWrite";
		}
		experienceWriteService.execute(experienceCommand);
		return "redirect:experienceList";
	}
	@GetMapping("experienceDetail")
	public String experienceDetail(String expNum, Model model) {
		experienceDetailService.execute(expNum, model);
		return "thymeleaf/experience/experienceDetail";
	}
	@GetMapping("experienceModify")
	public String experienceModify(String expNum, Model model) {
		experienceDetailService.execute(expNum, model);
		return "thymeleaf/experience/experienceModify";
	}
	@PostMapping("experienceModify")
	public String experienceModify(@Validated ExperienceCommand experienceCommand
			, BindingResult result) {
		experienceUpdateService.execute(experienceCommand);
		return "redirect:experienceDetail?expNum=" + experienceCommand.getExpNum();
	}
	@GetMapping("experienceDelete")
	public String experienceDelete(String expNum) {
		experienceDeleteService.execute(expNum);
		return "redirect:experienceList";
	}
}
