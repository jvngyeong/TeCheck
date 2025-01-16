package DDL.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ManageCommand {
	String manageNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "* 날짜를 입력해주세요.")
	Date manageDate;
	@NotEmpty(message = "* 내용을 입력해주세요.")
	String manageContents;
	@NotEmpty(message = "* 매장을 선택해주세요.")
	String storeNum;
	String empNum;
	
	String storeName;
}
