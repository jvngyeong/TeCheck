package DDL.command;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GoodsOrderCommand {
	String goodsOrderNum;
	Date goodsOrderDate;
	String empNum;
	@NotEmpty(message = "* 계약 번호는 필수 정보입니다.")
	String contractNum;
	@NotEmpty(message = "* 공급 업체번호는 필수 정보입니다.")
	String supNum;
	@NotNull(message = "* 발주 수령을 입력해주세요.")
	Integer goodsOrderQty;
	
	String goodsNum;
	String goodsName;
}
