package DDL.service.goodsIpgo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.GoodsIpgoCommand;
import DDL.domain.GoodsIpgoDTO;
import DDL.mapper.GoodsIpgoMapper;

@Service
public class IpgoUpdateService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	public void execute(GoodsIpgoCommand goodsIpgoCommand) {
		GoodsIpgoDTO dto = new GoodsIpgoDTO();
		BeanUtils.copyProperties(goodsIpgoCommand, dto);
		goodsIpgoMapper.goodsIpgoUpdate(dto);
		
	}

}
