package DDL.service.inquire;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.InquireDTO;
import DDL.mapper.InquireMapper;

@Service
public class InquireInfoService {
	@Autowired
	InquireMapper inquireMapper;
	public void execute(String goodsNum, String inquireNum, String expNum, Model model) {
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("goodsNum", goodsNum);
		map.put("inquireNum", inquireNum);
		map.put("expNum", expNum);
		List<InquireDTO> list = inquireMapper.inquireInfo(map);
		
		model.addAttribute("list", list);
		model.addAttribute("newLineChar", "\n");
	}
}
