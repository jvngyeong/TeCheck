package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("stockDTO")
public class StockDTO {
	Date tradeDate;
	String timestamp;
	Long volume;
	Long avgPrice;
	Long maxPrice;
	Long minPrice;
	Long openPrice;
	Long endPrice;
	Long totalPrice;
	Long totalVolume;
	String rateOfChange;
}
