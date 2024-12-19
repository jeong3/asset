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
	Date registDate;
	String mainImage;
	String mainStoreImage;
	String detailImage;
	String detailStoreImage;

}
