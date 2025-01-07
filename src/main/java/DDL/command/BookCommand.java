package DDL.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BookCommand {
	String bookNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date bookDate;
	String memberNum;
	@NotEmpty(message = "체험을 선택해주세요")
	String expNum;
	@NotEmpty(message = "매장을 선택해주세요")
	String storeNum;
	@NotEmpty(message = "상품을 선택해주세요")
	String goodsNum;
	String bookTime;
}
