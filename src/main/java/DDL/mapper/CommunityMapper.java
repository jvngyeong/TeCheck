package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.CommunityDTO;
import DDL.domain.CommunityReplyDTO;

@Mapper
public interface CommunityMapper {
	void communityWrite(CommunityDTO communityDTO);

	List<CommunityDTO> communityListSelect();

	CommunityDTO communitySelectOne(String commNum);

	void communityUpdate(CommunityDTO communityDTO);

	void communityDelete(String commNum);

	void insertReply(CommunityReplyDTO communityReplyDTO);

	List<CommunityReplyDTO> communityReplyListSelect(String commNum);

}
