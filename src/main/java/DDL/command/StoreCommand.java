package DDL.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class StoreCommand {
	String storeNum;
	@NotEmpty(message = "* 주소를 입력해주세요.")
	String storeAddr;
	String storeAddrDetail;
	@NotEmpty(message = "* 우편번호를 입력해주세요.")
	String storePost;
	@NotEmpty(message = "* 전화번호를 입력해주세요.")
	String storeTel;
	@NotEmpty(message = "* 매장 이름을 입력해주세요.")
	String storeName;
}
