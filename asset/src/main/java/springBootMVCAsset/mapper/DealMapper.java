package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import springBootMVCAsset.domain.AssetListDTO;
import springBootMVCAsset.domain.DealDTO;
import springBootMVCAsset.domain.SearchDTO;

@Mapper
public interface DealMapper {
	public void dealInsert(DealDTO dto);
	public List <DealDTO> dealList(SearchDTO dto);
	public Integer dealCount();
	public DealDTO dealDetail(String dealNum);
	public void dealUpdate(DealDTO dto);
	public void dealDelete(String dealNum);
	public List <DealDTO> assetDealList(
			@Param("dealMethodValue") String dealMethodValue
			, @Param("memberNum") String memberNum);
	public Integer totalSavePrice(
			@Param("categoryTypeValue") String dealMethodValue
			, @Param("memberNum") String memberNum);
	public Integer totalJaetechPrice(
			@Param("categoryTypeValue") String dealMethodValue
			, @Param("memberNum") String memberNum);
	public List <DealDTO> cashList(AssetListDTO assetListDTO);
	public List <DealDTO> bankList(AssetListDTO assetListDTO);
	public List <DealDTO> saveList(
			@Param("categoryType") String categoryType
			,AssetListDTO assetListDTO);
}
