package DDL.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import DDL.domain.InquireDTO;
import DDL.mapper.InquireMapper;
import DDL.service.inquire.InquireAnswerService;
import DDL.service.inquire.InquireInfoService;
import DDL.service.inquire.InquireWriteService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("inquire")
public class InquireController {
	@Autowired
	InquireMapper inquireMapper;
	@Autowired
	InquireInfoService inquireInfoService;
	@Autowired
	InquireWriteService inquireWriteService;
	@Autowired
	InquireAnswerService inquireAnswerService;
	@RequestMapping("inquireList")
	public String inquireList(@ModelAttribute("goodsNum") String goodsNum
			, Model model) {
		inquireInfoService.execute(goodsNum, null, null, model);
		return "thymeleaf/inquire/inquireList";
	}
	@GetMapping("inquireWrite")
	public String inquireWrite(@ModelAttribute(value="goodsNum") String goodsNum) {
		return "thymeleaf/inquire/inquireWrite";
	}
	@PostMapping("inquireWrite")
	public void inquireWrite(InquireDTO inquireDTO
			, HttpSession session, HttpServletResponse response) {
		inquireWriteService.execute(inquireDTO, session);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			String str = "<script>";
				   str += "window.self.close()";
				   str += "</script>";
			out.print(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@GetMapping("inquireUpdate")
	public String inquireUpdate(String inquireNum, Model model) {
		inquireInfoService.execute(null, inquireNum, null, model);
		return "thymeleaf/inquire/inquireUpdate";
	}
	@PostMapping("inquireUpdate")
	@ResponseBody
	public void inquireUpdate(InquireDTO inquireDTO , HttpServletResponse response) {
		inquireMapper.inquireUpdate(inquireDTO);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			String str = "<script>"
					   + "opener.parent.inquire();" 
					   + "window.self.close()" 
					   + "</script>";
			out.print(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("inquireDelete")
	public @ResponseBody void inquireDelete(String inquireNum) {
		inquireMapper.inquireDelete(inquireNum);
	}
	@GetMapping("questionList")
	public String questionList(Model model) {
		inquireInfoService.execute(null, null, null, model);
		return "thymeleaf/inquire/questionList";
	}
	@GetMapping("inquireAnswer")
	public String inquireAnswer(String inquireNum, Model model) {
		inquireInfoService.execute(null, inquireNum, null, model);
		return "thymeleaf/inquire/inquireAnswer";
	}
	@PostMapping("inquireAnswer")
	@ResponseBody
	public void inquireAnswer(InquireDTO inquireDTO, HttpSession session, HttpServletResponse response) {
		inquireAnswerService.execute(inquireDTO, session);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			String str = "<script>"
					   + "window.self.close();" 
					   + "</script>";
			out.print(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
