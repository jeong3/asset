package springBootMVCAsset.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("budgetDTO")
@Data
public class BudgetDTO {
	String budgetNum;
	String memberNum;
	Integer totalAsset;
	Integer totalCheck;
	Integer totalCash;
	Integer totalCredit;
}
