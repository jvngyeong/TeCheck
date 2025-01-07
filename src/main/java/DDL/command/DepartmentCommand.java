package DDL.command;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DepartmentCommand {
	String departmentNum;
	@NotEmpty(message = "* 부서명을 입력해주세요.")
	String departmentName;
	@NotEmpty(message = "* 부서 주소를 입력해주세요.")
	String departmentAddr;
	@NotEmpty(message = "* 부서 전화번호를 입력해주세요.")
	String departmentTel;
}
