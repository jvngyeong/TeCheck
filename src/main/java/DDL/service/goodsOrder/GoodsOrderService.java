package DDL.service.goodsOrder;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.GoodsOrderCommand;
import DDL.domain.AuthInfoDTO;
import DDL.domain.GoodsOrderDTO;
import DDL.mapper.AutoNumMapper;
import DDL.mapper.EmployeeMapper;
import DDL.mapper.GoodsOrderMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsOrderService {
	@Autowired
	GoodsOrderMapper goodsOrderMapper;
	
	@Autowired
	AutoNumMapper autoNumMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(GoodsOrderCommand goodsOrderCommand, HttpSession session) {
		GoodsOrderDTO goodsOrderDTO = new GoodsOrderDTO();
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		BeanUtils.copyProperties(goodsOrderCommand, goodsOrderDTO);
		String goodsOrderNum = autoNumMapper.getAutoNum("G-order_", "9", "goods_order_num", "goods_order");
		String empNum = employeeMapper.getEmpNum(auth.getUserId());
		goodsOrderDTO.setGoodsOrderNum(goodsOrderNum);
		goodsOrderDTO.setEmpNum(empNum);
		goodsOrderMapper.goodsOrderInsert(goodsOrderDTO);
	}
}
