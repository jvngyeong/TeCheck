package DDL.service.book;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.BookCommand;
import DDL.domain.AuthInfoDTO;
import DDL.domain.BookDTO;
import DDL.mapper.BookMapper;
import DDL.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class BookUpdateService {
	@Autowired
	BookMapper bookMapper;
	@Autowired
	MemberMapper memberMapper;
	public void execute(BookCommand bookCommand, HttpSession session) {
		BookDTO dto = new BookDTO();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		
		String memberNum = memberMapper.getMemberNum(auth.getUserId());
		
		if(bookCommand.getMemberNum() == memberNum) {
			BeanUtils.copyProperties(bookCommand, dto);
			bookMapper.bookUpdate(dto);
		}
	}
}
