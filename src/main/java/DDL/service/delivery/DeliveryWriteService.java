package DDL.service.delivery;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.DeliveryCommand;
import DDL.domain.DeliveryDTO;
import DDL.mapper.DeliveryMapper;

@Service
public class DeliveryWriteService {
	@Autowired
	DeliveryMapper deliveryMapper;
	public void execute(DeliveryCommand deliveryCommand) {
		DeliveryDTO deliveryDTO = new DeliveryDTO();
		BeanUtils.copyProperties(deliveryCommand, deliveryDTO);
		deliveryMapper.deliveryWrite(deliveryDTO);
	}
}
