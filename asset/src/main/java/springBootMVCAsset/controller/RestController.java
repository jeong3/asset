package springBootMVCAsset.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import springBootMVCAsset.mapper.EmployeeMapper;
import springBootMVCAsset.service.FileDelService;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	@Autowired
	FileDelService fileDelService;
	@Autowired
	EmployeeMapper employeeMapper;
	@PostMapping("/file/fileDel")
	public int fileDel(String orgFile, String storeFile, HttpSession session) {
		return fileDelService.execute(orgFile, storeFile, session);
   }
	@RequestMapping("/employee/setDepartment")
	public void setDepartment(@RequestBody Map<String, Object> map) {
		employeeMapper.setDepartmentNumUpdate(map.get("departmentNum").toString(), map.get("empNum").toString());
	}
}
