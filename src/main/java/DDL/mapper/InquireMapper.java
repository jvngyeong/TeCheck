package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import DDL.domain.InquireDTO;

@Mapper
public interface InquireMapper {
	public List<InquireDTO> inquireInfo(String goodsNum, String inquireNum);

}
