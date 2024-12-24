package DDL.domain;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("goodsCartDTO")
public class GoodsCartDTO {
	GoodsDTO goodsDTO;
	CartDTO cartDTO;
}
