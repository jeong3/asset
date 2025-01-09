package springBootMVCAsset.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("purchase")
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {
	String purchaseNum;
	Date purchaseDate;
	BigDecimal purchasePrice;
	Integer discountPrice;
	Integer purchaseSale;
	String deliveryAddr;
	String deliveryAddrDetail;
	Integer deliveryPost;
	String deliveryTel;
	String message;
	String purchaseStatus;
	String memberNum;
	String deliveryName;
	String purchaseName;
	
	public PurchaseDTO(String purchaseNum, Date purchaseDate, BigDecimal purchasePrice, String deliveryAddr, String deliveryAddrDetail, Integer deliveryPost, String deliveryTel, String message, String purchaseStatus, String memberNum, String deliveryName, String purchaseName) {
	    this.purchaseNum = purchaseNum;
	    this.purchaseDate = purchaseDate;
	    this.purchasePrice = purchasePrice;
	    this.deliveryAddr = deliveryAddr;
	    this.deliveryAddrDetail = deliveryAddrDetail;
	    this.deliveryPost = deliveryPost;
	    this.deliveryTel = deliveryTel;
	    this.message = message;
	    this.purchaseStatus = purchaseStatus;
	    this.memberNum = memberNum;
	    this.deliveryName = deliveryName;
	    this.purchaseName = purchaseName;
	}
}
