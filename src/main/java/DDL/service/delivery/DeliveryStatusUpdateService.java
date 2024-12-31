package DDL.service.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DDL.mapper.DeliveryMapper;

@Service
public class DeliveryStatusUpdateService {
	@Autowired
	DeliveryMapper deliveryMapper;
	public void execute(String orderNum) {
		deliveryMapper.deliveryStatusUpdate(orderNum);
	}
}
