package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("stockDTO")
public class StockDTO {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date tradeDate;
	String timestamp;
	Integer volume;
	Integer price;
	Long avgPrice;
	Long maxPrice;
	Long minPrice;
	Long openPrice;
	Long endPrice;
	Long totalPrice;
	Long totalVolume;
	String rateOfChange;
}
