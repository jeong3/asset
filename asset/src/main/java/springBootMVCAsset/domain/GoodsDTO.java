package springBootMVCAsset.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("goodsDTO")
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDTO {
	String goodsNum;
	String goodsName;
	String goodsContents;
	Integer goodsPrice;
	String empNum;
	String goodsKind;
	Date goodsRegist;
	String goodsMainImage;
	String goodsMainStoreImage;
	String goodsDetailImage;
	String goodsDetailStoreImage;
}