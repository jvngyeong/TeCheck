package DDL.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.domain.InquireDTO;
import DDL.mapper.InquireMapper;


@Controller
@RequestMapping("inquire")
public class InquireController {
	@Autowired
	InquireMapper inquireMapper;
	@RequestMapping("inquireList")
	public String inquireList(@ModelAttribute("goodsNum") String goodsNum
			,Model model) {
		List<InquireDTO> list = inquireMapper.inquireInfo(goodsNum, null);
		model.addAttribute("list", list);
		model.addAttribute("newLineChar", "\n");
		return "thymeleaf/inquire/inquireList";
	}
	
}
