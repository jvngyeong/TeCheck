package DDL.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("supplyContractDTO")
public class SupplyContractDTO {
	SupplyDTO supplyDTO;
	ContractDTO contractDTO;
	GoodsDTO goodsDTO;
}
