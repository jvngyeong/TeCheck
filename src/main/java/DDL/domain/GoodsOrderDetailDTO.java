package DDL.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("goodsOrderDetailDTO")
public class GoodsOrderDetailDTO {
	GoodsOrderDTO goodsOrderDTO;
	GoodsDTO goodsDTO;
	ContractDTO contractDTO;
}
