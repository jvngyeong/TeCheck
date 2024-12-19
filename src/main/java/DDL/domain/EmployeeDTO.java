package DDL.domain;



import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("employeeDTO")
public class EmployeeDTO {
    String empNum;
    String empId;
    String empPw;
    String empName;
    String empAddr;
    String empAddrDetail;
    String empPost;
    String empPhone;
    String empImage;
    String empStoreImage;
    Date empHireDate;
    String departmentNum;
    String empEmail;
}