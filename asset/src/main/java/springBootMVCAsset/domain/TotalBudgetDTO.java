package springBootMVCAsset.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("totalBudgetDTO")
@Data
public class TotalBudgetDTO {
	Integer totalAsset;
	Integer totalCheck;
	Integer totalCash;
	Integer totalCredit;
}
