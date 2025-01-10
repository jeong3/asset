package springBootMVCAsset.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;

@Alias("assetListDTO")
@Data
@AllArgsConstructor
public class AssetListDTO {
	int startRow;
	int endRow;
	String searchWord;
	String memberNum;
	
	public AssetListDTO() {}
}
