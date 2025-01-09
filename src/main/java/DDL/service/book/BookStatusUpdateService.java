package DDL.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.BookMapper;

@Service
public class BookStatusUpdateService {
	@Autowired
	BookMapper bookMapper;
	public void execute(String bookNum, String bookStatus) {
		bookMapper.bookStatusUpdate(bookNum, bookStatus);
		
	}
}
