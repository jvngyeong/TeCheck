package DDL.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmployeeCommand {
	String empNum;
	@NotEmpty(message = "* 아이디를 입력해주세요. ")
	String empId;
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
			message = "* 영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
	String empPw;
	@NotEmpty(message = "* 이름을 입력해주세요. ")
	String empName;
	@NotEmpty(message = "* 주소를 입력해주세요. ")
	String empAddr;
	String empAddrDetail;
	@NotEmpty(message = "* 우편번호를 입력해주세요. ")
	String empPost;
	@NotEmpty(message = "* 전화번호를 입력해주세요. ")
	String empPhone;
	MultipartFile empImage;
	String empStoreImage;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date empHireDate;
	@NotEmpty(message = "* 부서를 선택해주세요. ")
	String departmentNum;
	@NotEmpty(message = "* 이메일을 입력해주세요. ")
	String empEmail;
}
