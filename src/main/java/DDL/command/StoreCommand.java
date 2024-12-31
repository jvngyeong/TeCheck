package DDL.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class StoreCommand {
	String storeNum;
	@NotBlank(message = "주소를 입력하여 주세요.")
	String storeAddr;
	String storeAddrDetail;
	String storePost;
	String storeTel;
	@NotEmpty(message = "이름을 입력해주세요")
	String storeName;
}
