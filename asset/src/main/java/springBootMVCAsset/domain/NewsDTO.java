package springBootMVCAsset.domain;

import java.util.Date;

import lombok.Data;

@Data
public class NewsDTO {
		String newsNum;        // 뉴스 번호
		String newsTitle;     // 뉴스 제목
		String newsContents;   // 뉴스 내용
		String imageTitle;    // 이미지 제목
		String imageContents;  // 이미지 내용
		String image;  // 업로드된 이미지 파일
		String storeImage;     // STORE_IMAGE
	    Date registDate;  // REGIST_DATE
	    Date updateDate;  // UPDATE_DATE
	    String empNum;            // EMP_NUM
	    String updateEmpNum;      // UPDATE_EMP_NUM
	    int visitCount;

}
