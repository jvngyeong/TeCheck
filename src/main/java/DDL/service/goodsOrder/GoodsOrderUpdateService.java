package DDL.service.goodsOrder;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.GoodsOrderCommand;
import DDL.domain.AuthInfoDTO;
import DDL.domain.GoodsOrderDTO;
import DDL.mapper.EmployeeMapper;
import DDL.mapper.GoodsOrderMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsOrderUpdateService {
	@Autowired
	GoodsOrderMapper goodsOrderMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(GoodsOrderCommand goodsOrderCommand, HttpSession session) {
		GoodsOrderDTO goodsOrderDTO = new GoodsOrderDTO();
		BeanUtils.copyProperties(goodsOrderCommand, goodsOrderDTO);
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String empNum = employeeMapper.getEmpNum(auth.getUserId());
		goodsOrderDTO.setEmpNum(empNum);
		goodsOrderMapper.goodsOrderUpdate(goodsOrderDTO);		
	}
}
