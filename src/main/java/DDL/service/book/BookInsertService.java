package DDL.service.book;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.BookCommand;
import DDL.domain.AuthInfoDTO;
import DDL.domain.BookDTO;
import DDL.mapper.AutoNumMapper;
import DDL.mapper.BookMapper;
import DDL.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class BookInsertService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	AutoNumMapper autoNumMapper;
	@Autowired
	BookMapper bookMapper;
	public void execute(BookCommand bookCommand, HttpSession session) {
		BookDTO dto = new BookDTO();
		BeanUtils.copyProperties(bookCommand, dto);
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = memberMapper.getMemberNum(auth.getUserId());
		dto.setMemberNum(memberNum);
		
		String bookNum = autoNumMapper.getAutoNum("book_", "6", "book_num", "book");
		dto.setBookNum(bookNum);
		
		dto.setBookTime(java.time.LocalTime.parse(bookCommand.getBookTime()));
		
		bookMapper.bookInsert(dto);
	}
}
