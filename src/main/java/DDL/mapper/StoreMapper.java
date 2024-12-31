package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.StoreDTO;

@Mapper
public interface StoreMapper {
	public Integer storeInsert(StoreDTO dto);
	public List<StoreDTO> storeSelectAll();
	public StoreDTO storeSelectOne(String storeNum);
	public Integer storeUpdate(StoreDTO dto);
	public Integer storeDelete(String storeNum);

}
