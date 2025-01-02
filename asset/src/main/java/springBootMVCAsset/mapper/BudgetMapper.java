package springBootMVCAsset.mapper;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.BudgetDTO;

@Mapper
public interface BudgetMapper {
	public void budgetRegist(BudgetDTO dto);
	public void budgetUpdate(String memberNum);
	public BudgetDTO budgetDetail(String memberNum);
}
