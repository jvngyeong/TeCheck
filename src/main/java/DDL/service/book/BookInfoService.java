package DDL.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.AuthInfoDTO;
import DDL.domain.BookStoreGoodsExpDTO;
import DDL.mapper.BookMapper;
import DDL.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class BookInfoService {
	@Autowired
	BookMapper bookMapper;
	@Autowired
	MemberMapper memberMapper;
	public String execute(Model model, HttpSession session, String bookNum) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = null;
		try {
			memberNum = memberMapper.getMemberNum(auth.getUserId());
		}catch(Exception e) {
			e.printStackTrace();
			return "000";  // session이 없다
		}
		if(memberNum == null) {
			return "900";
		}else {
			BookStoreGoodsExpDTO dto = bookMapper.bookSelectInfo(bookNum, memberNum);
			model.addAttribute("dto", dto);
			return "200";
		}
	}
}
