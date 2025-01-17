package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.ManageDTO;

@Mapper
public interface ManageMapper {
	public Integer manageInsert(ManageDTO dto);

	public List<ManageDTO> manageSelectList();

	public ManageDTO manageSelectOne(String manageNum);

	public Integer manageUpdate(ManageDTO dto);

	public Integer manageDelete(String manageNum);



}
