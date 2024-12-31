package DDL.service.goodsIpgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.GoodsIpgoMapper;

@Service
public class IpgoDeleteService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	public void execute(String ipgoNum, String goodsNum) {
		goodsIpgoMapper.goodsIpgoDelete(ipgoNum, goodsNum);
	}
}
