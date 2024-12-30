package springBootMVCAsset.command;

import lombok.Data;

@Data
public class CartCommand {
	String goodsNum;
	Integer cartQty;
}
