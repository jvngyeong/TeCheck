package DDL.command;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SupplyCommand {
	String supNum;
	@NotEmpty(message = "* 업체 이름을 입력해주세요.")
	String supName;
	@NotEmpty(message = "* 사업자등록번호를 입력해주세요.")
	String businessNum;
	@NotEmpty(message = "* 업체 전화번호를 입력해주세요.")
	String supTel;
	@NotEmpty(message = "* 업체 주소를 입력해주세요.")
	String supAddr;
	@NotEmpty(message = "* 담당자 이름을 입력해주세요.")
	String managerName;
	@NotEmpty(message = "* 담당자 연락처를 입력해주세요.")
	String managerPhone;
	String contractNum;
	@NotEmpty(message = "* 계약 시작일을 입력해주세요.")
	String contractStart;
	@NotEmpty(message = "* 계약 만료일을 입력해주세요.")
	String contractEnd;
	String contractPeriod;
	@NotEmpty(message = "* 납입계좌를 입력해주세요.")
	String payaccount;
	@NotEmpty(message = "* 납입일을 입력해주세요.")
	String duedate;
	@NotEmpty(message = "* 상품을 선택해주세요.")
	String goodsNum;
}
