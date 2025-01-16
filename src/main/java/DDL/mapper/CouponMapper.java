package DDL.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import DDL.domain.CouponDTO;
import DDL.domain.MemberCouponDTO;

@Mapper
public interface CouponMapper {
	void couponInsert(CouponDTO couponDTO);

	List<CouponDTO> couponListSelect();

	CouponDTO couponSelectOne(String couponNum);

	void couponUpdate(CouponDTO couponDTO);

	void couponDelete(String couponNum);

	void memberCouponWrite(MemberCouponDTO memberCouponDTO);
}
