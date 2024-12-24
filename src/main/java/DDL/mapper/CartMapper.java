package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.CartDTO;
import DDL.domain.GoodsCartDTO;

@Mapper
public interface CartMapper {

	List<GoodsCartDTO> cartSelect(String memberNum);

	void cartMerge(CartDTO cartDTO);

	int cartDelete(String goodsNum, String memberNum);

	void cartUpdate(String cartQty, String goodsNum, String memberNum);
}
