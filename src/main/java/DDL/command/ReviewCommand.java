package DDL.command;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ReviewCommand {
	String reviewNum;
	String bookNum;
	String reviewDate;
	@NotEmpty(message = "리뷰를 작성해주세요.")
	String reviewContent;
	String orderNum;
	String goodsNum;
}
