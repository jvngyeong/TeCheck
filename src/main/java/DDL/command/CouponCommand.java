package DDL.command;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CouponCommand {
	String couponNum;
	@NotEmpty(message = "* 쿠폰 이름을 작성해주세요.")
	String couponName;
	@NotNull(message = "* 쿠폰 할인율을 입력해주세요.")
	Integer discount;
}
