package DDL.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberCouponCommand {
	String issueNum;
	
	String couponNum;
	
	@NotEmpty(message = "* 쿠폰 이름을 작성해주세요.")
	String couponName;
	
	@NotNull(message = "* 쿠폰 할인율을 입력해주세요.")
	Integer discount;
	
	@NotNull(message = "* 회원을 선택해주세요.")
	String memberNum;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "* 쿠폰 개시일을 선택해주세요.")
	Date couponStart;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "* 쿠폰 만료일을 선택해주세요.")
	Date couponExpire;
}
