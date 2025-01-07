package DDL.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.BookMapper;

@Service
public class BookDeleteService {
	@Autowired
	BookMapper bookMapper;
	public void execute(String bookNum) {
		bookMapper.bookDelete(bookNum);
	}
}
