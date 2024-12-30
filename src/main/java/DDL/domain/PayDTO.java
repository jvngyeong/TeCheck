package DDL.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("payDTO")
public class PayDTO {
	String orderNum;
	String confirmnumber;
	String cardnum;
	String tid;
	String totalprice;
	String resultmessage;
	String paymethod;
	String appldate;
	String apptime;
	String purchaseName;
}
