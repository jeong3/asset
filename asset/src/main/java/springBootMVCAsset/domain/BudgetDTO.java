package springBootMVCAsset.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("budgetDTO")
@Data
public class BudgetDTO {
	String budgetNum;
	String memberNum;
	Integer budgetPrice;
	Integer remainPrice;
	String categoryName;
	String dealMethod;
}
