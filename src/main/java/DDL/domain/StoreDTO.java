package DDL.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("storeDTO")
public class StoreDTO {
	String storeNum;
	String storeAddr;
	String storeAddrDetail;
	String storePost;
	String storeTel;
	String storeName;
}
