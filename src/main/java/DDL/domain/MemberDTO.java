package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("memberDTO")
public class MemberDTO {
	String memberNum;
	String memberId;
	String memberPw;
	String memberName;
	String memberPhone;
	Integer memberAge;
	String memberAddr;
	String memberAddrDetail;
	String memberPost;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memberBirth;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memberRegist;
	String gender;
	String memberEmail;
}