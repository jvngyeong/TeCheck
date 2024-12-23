package DDL.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberCommand {
	String memberNum;
	@NotEmpty(message = "아이디를 입력해주세요")
	String memberId;
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
			message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
	String memberPw;
	String memberName;
	String memberPhone;
	String memberAddr;
	String memberAddrDetail;
	String memberPost;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memberBirth;
	String gender;
	String memberEmail;
}
