package DDL.mapper;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.DeliveryDTO;

@Mapper
public interface DeliveryMapper {

	public void deliveryWrite(DeliveryDTO deliveryDTO);

	public void deliveryDelete(String deliveryNum);

	public void deliveryStatusUpdate(String orderNum);

}
