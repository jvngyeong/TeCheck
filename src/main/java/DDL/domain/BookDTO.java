package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("bookDTO")
public class BookDTO {
	String bookNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date bookDate;   
	String memberNum;
	String expNum;
	String empNum;
	String storeNum;
	String goodsNum;
}