package DDL.service.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.CommunityCommand;
import DDL.domain.AuthInfoDTO;
import DDL.domain.CommunityDTO;
import DDL.domain.CommunityReplyDTO;
import DDL.mapper.CommunityMapper;
import DDL.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class CommunityReplyService {
	@Autowired
	CommunityMapper communityMapper;
	@Autowired
	MemberMapper memberMapper;
	public void execute(CommunityCommand communityCommand, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberId = auth.getUserId();
		String memberNum = memberMapper.getMemberNum(memberId);
		
		// CommunityCommand에서 댓글 정보를 추출하여 DTO 생성
        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setMemberId(memberId);
        communityDTO.setMemberNum(memberNum);
        communityDTO.setCommNum(communityCommand.getCommNum());
        
        CommunityReplyDTO communityReplyDTO = new CommunityReplyDTO();
        communityReplyDTO.setReplyContent(communityCommand.getReplyContent());
        communityReplyDTO.setReplyDate(communityCommand.getReplyDate());
        // 댓글 저장 (Mapper 호출)
        communityMapper.insertReply(communityDTO);
    }

}
