package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("inquireDTO")
public class InquireDTO {
	String inquireNum;
	String inquireTitle;
	String inquireContents;
	Date inquireDate;
	String inquireKind;
	String inquireAnswer;
	Date inquireAnswerDate;
	
	String goodsNum;
	String expNum;
	String goodsName;
	String expName;
	
	String memberId;
	String memberNum;
	String empNum;
}
