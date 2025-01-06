package DDL.command;

import lombok.Data;

@Data
public class FindCommand {
	String userId;
	String userName;
	String userPhone;
	String userEmail;
	String userPw;
	String userPwCon;
	String grade;
}
