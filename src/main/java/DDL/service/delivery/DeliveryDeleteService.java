package DDL.service.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.command.DeliveryCommand;
import DDL.mapper.DeliveryMapper;

@Service
public class DeliveryDeleteService {
	@Autowired
	DeliveryMapper deliveryMapper;
	public void execute(DeliveryCommand deliveryCommand) {
		deliveryMapper.deliveryDelete(deliveryCommand.getDeliveryNum());
	}
}
