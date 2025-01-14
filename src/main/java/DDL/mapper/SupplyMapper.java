package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.ContractDTO;
import DDL.domain.SupplyContractDTO;
import DDL.domain.SupplyDTO;

@Mapper
public interface SupplyMapper {

	void supplyInsert(SupplyDTO supplyDTO);

	void contractInsert(ContractDTO contractDTO);

	List<SupplyContractDTO> supplyListSelect();

	SupplyContractDTO supplySelectOne(String supNum);

	void supplyUpdate(SupplyDTO supplyDTO);

	void contractUpdate(ContractDTO contractDTO);

	void contractDelete(String supNum);

	void supplyDelete(String supNum);
}
