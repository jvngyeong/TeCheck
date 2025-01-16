package DDL.service.manage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.ManageCommand;
import DDL.domain.AuthInfoDTO;
import DDL.domain.ManageDTO;
import DDL.mapper.EmployeeMapper;
import DDL.mapper.ManageMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class ManageInsertService {
	@Autowired
	ManageMapper manageMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(ManageCommand manageCommand, HttpSession session) {
		ManageDTO dto = new ManageDTO();
		BeanUtils.copyProperties(manageCommand, dto);
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String empNum = employeeMapper.getEmpNum(auth.getUserId());
		dto.setEmpNum(empNum);
		
		manageMapper.manageInsert(dto);
	}
}
