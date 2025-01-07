package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.CommunityDTO;
import DDL.domain.CommunityMemberDTO;
import DDL.domain.CommunityReplyDTO;
import DDL.domain.CommunityReplyMemberDTO;

@Mapper
public interface CommunityMapper {
	void communityWrite(CommunityDTO communityDTO);

	List<CommunityMemberDTO> communityListSelect();

	CommunityMemberDTO communitySelectOne(String commNum);

	void communityUpdate(CommunityDTO communityDTO);

	void communityDelete(String commNum);

	void insertReply(CommunityReplyDTO communityReplyDTO);

	List<CommunityReplyMemberDTO> communityReplyListSelect(String commNum);
	

}
