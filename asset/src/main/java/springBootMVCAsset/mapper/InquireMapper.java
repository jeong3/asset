package springBootMVCAsset.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootMVCAsset.domain.InquireDTO;
@Mapper
public interface InquireMapper {
	public List<InquireDTO> inquireList(String goodsNum, Integer inquireNum);
	public Integer inquireInsert(InquireDTO dto);
	public Integer inquireUpdate(InquireDTO dto);
	public Integer inquireDelete(Integer inquireNum);
	public Integer inquireAnswerUpdate(InquireDTO dto);
}
