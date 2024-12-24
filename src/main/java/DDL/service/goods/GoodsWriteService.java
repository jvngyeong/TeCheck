package DDL.service.goods;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import DDL.command.GoodsCommand;
import DDL.domain.GoodsDTO;
import DDL.mapper.AutoNumMapper;
import DDL.mapper.GoodsMapper;

@Service
public class GoodsWriteService {
    @Autowired
    GoodsMapper goodsMapper;
    
    @Autowired
    AutoNumMapper autoNumMapper;
    public void execute(GoodsCommand goodsCommand) {
        GoodsDTO goodsDTO = new GoodsDTO();
        BeanUtils.copyProperties(goodsCommand, goodsDTO);
        String goodsNum = autoNumMapper.getAutoNum("goods_", "7", "goods_num", "goods");
        goodsDTO.setGoodsNum(goodsNum);
        ////// 파일 추가
 		/// 경로
 		URL resource = getClass().getClassLoader().getResource("static/upload");
 		System.out.println("resource : " + resource);
 		String filrDir = resource.getFile();
 		//String filrDir = "C:/Users/301-14/eclipse-final/TeCheck/target/classes/static/upload";
 		////////파일 관련 내용
 		//  메인이미지
 		MultipartFile mf = goodsCommand.getGoodsMainImage();
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
 		/// dto에저장
 		goodsDTO.setGoodsMainImage(originalFile);
 		goodsDTO.setGoodsMainStoreImage(storeFileName);
 		////
 		if(!goodsCommand.getGoodsDetailImage()[0].getOriginalFilename().isEmpty()) {
 			String originalTotal = ""; 
 			String storeTotal = "";
 			for(MultipartFile mpf : goodsCommand.getGoodsDetailImage()) {
 				originalFile = mpf.getOriginalFilename();//오류
 				extension = originalFile.substring(originalFile.lastIndexOf("."));
 				storeName = UUID.randomUUID().toString().replace("-", "");
 				storeFileName = storeName + extension;
 				file = new File(filrDir + "/" + storeFileName);
 				try {
 					mpf.transferTo(file);
 				} catch (Exception e) {
 					e.printStackTrace();
 				}
 				originalTotal += originalFile + "/";
 				storeTotal += storeFileName +"/";
 			}
 			goodsDTO.setGoodsDetailImage(originalTotal);
 			goodsDTO.setGoodsDetailStoreImage(storeTotal);
 		}
        goodsMapper.goodsWrite(goodsDTO);
    }
}