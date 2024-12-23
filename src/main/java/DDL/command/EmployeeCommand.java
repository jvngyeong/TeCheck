package DDL.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeCommand {
	String empNum;
	@Size(min = 8, max = 12)
	String empId;
	@Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{8,}$", 
			 message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
	String empPw;
	String empName;
	String empAddr;
	String empAddrDetail;
	String empPost;
	String empPhone;
	String empImage;
	String empStoreImage;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date empHireDate;
	String departmentNum;
	String empEmail;
}
