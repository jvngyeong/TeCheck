package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("stockDTO")
public class StockDTO {
	Date tradeDate;
	String timestamp;
	Integer volume;
	Integer price;
}
