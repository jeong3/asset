package springBootMVCAsset.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import springBootMVCAsset.domain.AssetListDTO;
import springBootMVCAsset.domain.DealDTO;
import springBootMVCAsset.domain.SearchDTO;

@Mapper
public interface DealMapper {
	public void dealInsert(DealDTO dto);
	public List <DealDTO> dealList(SearchDTO dto);
	// 카운트
	public Integer dealCount(String memberNum);
	public Integer bankDealCount(String memberNum);
	public Integer dealSaveCount(String memberNum, String categoryName, String categoryType);
	public Integer dealjaetechCount(String memberNum, String categoryName, String categoryType);
	
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
			,@Param("assetListDTO") AssetListDTO assetListDTO);
	public List <DealDTO> jaetechList(
			@Param("categoryType") String categoryType
			,@Param("assetListDTO") AssetListDTO assetListDTO);
	public List <DealDTO> creditList(AssetListDTO assetListDTO);
	
	public Integer monthEx(
			@Param("dealStartDate") Date dealStartDate
			, @Param("dealEndDate") Date dealEndDate
			, @Param("memberNum") String memberNum);
	public Integer monthIm(
			@Param("dealStartDate") Date dealStartDate
			, @Param("dealEndDate") Date dealEndDate
			, @Param("memberNum") String memberNum);
	
	public Integer todayEx(String memberNum);
	public Integer todayIm(String memberNum);
}
