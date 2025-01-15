package DDL.service.inquire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.InquireDTO;
import DDL.mapper.InquireMapper;

@Service
public class InquireMineListService {
	@Autowired
	InquireMapper inquireMapper;
	public void execute(String memberNum, Model model) {
		List<InquireDTO> list = inquireMapper.inquireMine(memberNum);
		
		model.addAttribute("list", list);
		model.addAttribute("newLineChar", "\n");
	}
}
