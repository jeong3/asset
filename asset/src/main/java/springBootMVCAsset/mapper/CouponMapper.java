package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.CouponDTO;

@Mapper
public interface CouponMapper {
	public void couponInsert(String memberNum);
	public List<CouponDTO> couponSelect(String memberNum);
	public void couponDelete(String couponNum);
	public void addCoupon(String memberNum);
}
