package DDL.service.goodsOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.GoodsOrderMapper;

@Service
public class GoodsOrderDeleteService {
	@Autowired
	GoodsOrderMapper goodsOrderMapper;
	public void execute(String goodsOrderNum) {
		goodsOrderMapper.goodsOrderDelete(goodsOrderNum);
	}
}
