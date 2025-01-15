package DDL.service.inquire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.domain.AuthInfoDTO;
import DDL.domain.InquireDTO;
import DDL.mapper.AutoNumMapper;
import DDL.mapper.InquireMapper;
import DDL.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class InquireExpWriteService {
	@Autowired
	InquireMapper inquireMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	AutoNumMapper autoNumMapper;
	public void execute(InquireDTO inquireDTO, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO	)session.getAttribute("auth");
		String memberNum = memberMapper.getMemberNum(auth.getUserId());
		inquireDTO.setMemberNum(memberNum);
		String inquireNum = autoNumMapper.getAutoNum("inq_", "5", "inquire_num", "inquire");
		inquireDTO.setInquireNum(inquireNum);
		inquireDTO.setExpNum(inquireDTO.getInquireKind());
		
		inquireMapper.inquireExpInsert(inquireDTO);
	}

}
