package DDL.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("communityReplyMemberDTO")
public class CommunityReplyMemberDTO {
	MemberDTO memberDTO;
	CommunityReplyDTO communityReplyDTO;
}
