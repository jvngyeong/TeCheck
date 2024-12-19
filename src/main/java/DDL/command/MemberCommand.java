package DDL.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberCommand {
	String memberNum;
	String memberId;
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
