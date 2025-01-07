package DDL.command;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class FindCommand {
	@NotEmpty(message = "* 아이디를 입력해주세요.")
	String userId;
	@NotEmpty(message = "* 이름을 입력해주세요.")
	String userName;
	@NotEmpty(message = "* 전화번호를 입력해주세요.")
	String userPhone;
	@NotEmpty(message = "* 이메일을 입력해주세요.")
	String userEmail;
	String userPw;
	String userPwCon;
	String grade;
}