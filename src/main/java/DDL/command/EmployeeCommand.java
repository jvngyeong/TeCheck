package DDL.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmployeeCommand {
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date empHireDate;
    String departmentNum;
    String empEmail;
}