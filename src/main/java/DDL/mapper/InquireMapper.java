package DDL.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.InquireDTO;

@Mapper
public interface InquireMapper {
	public List<InquireDTO> inquireInfo(Map<String, Object> map);
	public Integer inquireInsert(InquireDTO inquireDTO);
	public Integer inquireUpdate(InquireDTO inquireDTO);
	public Integer inquireDelete(String inquireNum);
	public Integer inquireAnswerUpdate(InquireDTO inquireDTO);
	public List<InquireDTO> inquireMine(String memberNum);
	public List<InquireDTO> inquireExp(String expNum);
	public Integer inquireExpInsert(InquireDTO inquireDTO);
}
