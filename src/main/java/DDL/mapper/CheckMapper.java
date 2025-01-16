package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.CheckDTO;

@Mapper
public interface CheckMapper {

	public Integer checkWrite(CheckDTO dto);

	public List<CheckDTO> checkSelectList();

	public CheckDTO checkSelectOne(String checkNum);

	public Integer checkUpdate(CheckDTO dto);

	public Integer checkDelete(String checkNum);

}
