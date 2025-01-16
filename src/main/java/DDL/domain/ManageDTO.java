package DDL.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("manageDTO")
public class ManageDTO {
	String manageNum;
	Date manageDate;
	String manageContents;
	String storeNum;
	String empNum;
	
	String storeName;
}
