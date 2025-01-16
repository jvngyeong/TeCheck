package DDL.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CheckCommand {
	String checkNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "* 날짜를 입력해주세요.")
	Date checkDate;
	@NotEmpty(message = "* 내용을 입력해주세요.")
	String checkResult;
	@NotEmpty(message = "* 상품을 선택해주세요.")
	String goodsNum;
	String empNum;
	@NotEmpty(message = "* 매장을 선택해주세요.")
	String storeNum;
	
	String goodsName;
	String storeName;
}
