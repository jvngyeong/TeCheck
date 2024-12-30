package DDL.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.OrderDTO;
import DDL.domain.PayDTO;

@Mapper
public interface OrderMapper {

	OrderDTO orderSelectOne(String orderNum);

	void orderInsert(OrderDTO dto);

	void purchaseListInsert(Map<String, Object> map);

	String getOrderNum();

	void payInsert(PayDTO dto);

	void purchaseStatusUpdate(String orderNum);

	List<OrderDTO> orderSelectByMemberNum(String memberNum);
}
