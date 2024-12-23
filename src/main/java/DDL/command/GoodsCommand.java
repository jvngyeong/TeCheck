package DDL.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class GoodsCommand {
	String goodsNum;
	@NotEmpty(message = "이름을 입력하세요")
	String goodsName;
	@NotNull(message = "가격을 입력하세요")
	Integer goodsPrice;
	@NotEmpty(message = "설명을 입력하세요")
	String goodsContents;
	Integer visitCount;
	String goodsMainImage;
	String goodsDetailImage[];
	String goodsMainStoreImage;
	String goodsDetailStoreImage[];
	
//	MultipartFile goodsMainImage;
//	MultipartFile goodsDetailImage[];
//    MultipartFile goodsMainStoreImage;
//	MultipartFile goodsDetailStoreImage[];
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date goodsRegist;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date goodsUpdateDate;
}