package DDL.service.community;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.CommunityReplyCommand;
import DDL.domain.AuthInfoDTO;
import DDL.domain.CommunityReplyDTO;
import DDL.mapper.AutoNumMapper;
import DDL.mapper.CommunityMapper;
import DDL.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class CommunityReplyService {
	@Autowired
	CommunityMapper communityMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	AutoNumMapper autoNumMapper;
	public void execute(CommunityReplyCommand communityReplyCommand, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberId = auth.getUserId();
		String memberNum = memberMapper.getMemberNum(memberId);
		CommunityReplyDTO communityReplyDTO = new CommunityReplyDTO();
		BeanUtils.copyProperties(communityReplyCommand, communityReplyDTO);
		String replyNum = autoNumMapper.getAutoNum("rep_", "5", "reply_num", "reply");
		communityReplyDTO.setMemberNum(memberNum);
		communityReplyDTO.setReplyNum(replyNum);
		// 댓글 저장 (Mapper 호출)
        communityMapper.insertReply(communityReplyDTO);
    }

}
