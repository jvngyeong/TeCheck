package DDL.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberCommand {
	String memberNum;
	@NotEmpty(message = "* 아이디를 입력해주세요.")
	String memberId;
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
			message = "* 영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
	String memberPw;
	@NotEmpty(message = "* 이름을 입력해주세요.")
	String memberName;
	@NotEmpty(message = "* 전화번호를 입력해주세요.")
	String memberPhone;
	@NotEmpty(message = "* 주소를 입력해주세요.")
	String memberAddr;
	String memberAddrDetail;
	@NotEmpty(message = "* 우편번호를 입력해주세요.")
	String memberPost;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "* 생년월일을 입력해주세요.")
	Date memberBirth;
	@NotEmpty(message = "* 성별을 입력해주세요.")
	String gender;
	@NotEmpty(message = "* 이메일을 입력해주세요.")
	String memberEmail;
}
