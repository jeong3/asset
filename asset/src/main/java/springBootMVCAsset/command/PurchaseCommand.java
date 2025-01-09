package springBootMVCAsset.command;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PurchaseCommand {
	String goodsNums;
	BigDecimal  totalPaymentPrice;
	String purchaseName;
	String deliveryAddr;
	String deliveryAddrDetail;
	String deliveryPost;
	String deliveryTel;
	String message;	
}
