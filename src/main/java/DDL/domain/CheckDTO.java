package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("checkDTO")
public class CheckDTO {
	String checkNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date checkDate;
	String checkResult;
	String goodsNum;
	String empNum;
	String storeNum;
	
	String goodsName;
	String storeName;
}
