package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;
@Data
@Alias("couponDTO")
public class CouponDTO {
	String couponNum;
	String couponName;
	String couponPeriod;
	Integer couponPrice;
	Date registDate;
	String couponContents;
	String memberNum;
}
