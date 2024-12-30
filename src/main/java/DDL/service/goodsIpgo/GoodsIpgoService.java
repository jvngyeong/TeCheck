package DDL.service.goodsIpgo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.GoodsIpgoCommand;
import DDL.domain.AuthInfoDTO;
import DDL.domain.GoodsIpgoDTO;
import DDL.mapper.EmployeeMapper;
import DDL.mapper.GoodsIpgoMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsIpgoService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(GoodsIpgoCommand goodsIpgoCommand, HttpSession session) {
		GoodsIpgoDTO dto = new GoodsIpgoDTO();
		BeanUtils.copyProperties(goodsIpgoCommand, dto);
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String empNum = employeeMapper.getEmpNum(auth.getUserId());
		
		dto.setEmpNum(empNum);
		
		goodsIpgoMapper.goodsIpgo(dto);
	}
}
