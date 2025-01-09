package DDL.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("bookStoreGoodsExpDTO")
public class BookStoreGoodsExpDTO {
	BookDTO bookDTO;
	StoreDTO storeDTO;
	GoodsDTO goodsDTO;
	ExperienceDTO experienceDTO;
	MemberDTO memberDTO;
}
