package springBootMVCAsset.command;

import lombok.Data;

@Data
public class PurchaseCommand {
	String goodsNums;
	Integer  totalPaymentPrice;
	String purchaseName;
	String deliveryAddr;
	String deliveryAddrDetail;
	String deliveryPost;
	String deliveryTel;
	String message;	
}
