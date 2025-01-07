package DDL.service.employee;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import DDL.command.EmployeeCommand;
import DDL.domain.EmployeeDTO;
import DDL.mapper.EmployeeMapper;

@Service
public class EmployeeUpdateService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(EmployeeCommand employeeCommand) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		
		////// 파일 추가
 		/// 경로
 		URL resource = getClass().getClassLoader().getResource("static/upload");
 		System.out.println("resource : " + resource);
 		String filrDir = resource.getFile();
 		////////파일 관련 내용
 		//  메인이미지
 		MultipartFile mf = employeeCommand.getEmpImage();
 		String originalFile = mf.getOriginalFilename();
 		/// 저장하기 위한 이름 만들기 : UUID : shfioshiof30750937skfhs
 		// 확장자 : .jpg, .png : abcd.abdc.jpg
 		String extension = originalFile.substring(originalFile.lastIndexOf("."));
 		// 이름 짖기
 		String storeName = UUID.randomUUID().toString().replace("-", "");
 		String storeFileName = storeName + extension;
 		// 파일 생성
 		File file = new File(filrDir + "/" + storeFileName);
 		try {
 			mf.transferTo(file);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
		BeanUtils.copyProperties(employeeCommand, employeeDTO);
		employeeDTO.setEmpImage(originalFile);
 		employeeDTO.setEmpStoreImage(storeFileName);
		employeeMapper.employeeUpdate(employeeDTO);
	}
}
