package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.BudgetDTO;
import springBootMVCAsset.domain.TotalBudgetDTO;

@Mapper
public interface BudgetMapper {
	public void budgetInsert(BudgetDTO dto);
	public TotalBudgetDTO totalBudgetList(String memberNum);
}
