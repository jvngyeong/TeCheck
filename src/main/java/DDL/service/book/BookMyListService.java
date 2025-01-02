package DDL.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.AuthInfoDTO;
import DDL.domain.BookStoreGoodsExpDTO;
import DDL.mapper.BookMapper;
import DDL.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class BookMyListService {
	@Autowired
	BookMapper bookMapper;
	@Autowired
	MemberMapper memberMapper;
	public void execute(Model model, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = memberMapper.getMemberNum(auth.getUserId());
		List<BookStoreGoodsExpDTO> list = bookMapper.bookSelectMine(memberNum);
		model.addAttribute("list", list);
	}
}
