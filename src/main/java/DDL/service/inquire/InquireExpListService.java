package DDL.service.inquire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.InquireDTO;
import DDL.mapper.InquireMapper;

@Service
public class InquireExpListService {
	@Autowired
	InquireMapper inquireMapper;
	public void execute(String expNum, Model model) {
		List<InquireDTO> list = inquireMapper.inquireExp(expNum);
		
		model.addAttribute("list", list);
		model.addAttribute("newLineChar", "\n");
	}

}
