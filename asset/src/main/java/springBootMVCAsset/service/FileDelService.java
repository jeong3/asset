package springBootMVCAsset.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.domain.FileDTO;

@Service
public class FileDelService {
   
   public int execute(String orgFile, String storeFile, HttpSession session) {
      int i = 0;
      FileDTO dto = new FileDTO(); // 세션에는 하나씩밖에 저장이 안되기 때문에 dto에 저장해서 세션에 저장해야한다.
      dto.setOrgFile(orgFile);
      dto.setStoreFile(storeFile);
      Boolean newFile = true;
      
      //세션의 생성여부 확인
      List<FileDTO> list = (List<FileDTO>) session.getAttribute("fileList");
      if(list == null) { // 세션이 없을 때 생성 
         list = new ArrayList<FileDTO>();
         
      } 
      // 세션이 있을 때 삭제
         for(FileDTO fd : list) {
            if(fd.getStoreFile().equals(storeFile)) {
               list.remove(fd);
               newFile = false;
               break;
            }
         
      }
      if(newFile) {
         list.add(dto);
         i = 1;
      }
      session.setAttribute("fileList", list); // 리스트가 null일 때는 세션이 생성이 안되기 때문에
      return i;
   }

}
