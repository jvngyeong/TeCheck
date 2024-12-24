package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.MemberDTO;

@Mapper
public interface MemberMapper {
   public Integer memberInsert(MemberDTO dto);
   public List<MemberDTO> memberSelectAll();
   public MemberDTO memberSelectOne(String memberNum);
   public Integer memberUpdate(MemberDTO dto);
   public Integer memberDelete(String memberNum);
   public String getMemberNum(String memberId);
}