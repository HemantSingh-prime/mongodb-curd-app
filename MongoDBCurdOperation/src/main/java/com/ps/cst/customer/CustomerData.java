package com.ps.cst.customer;




import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "CUSTOMER_DATA")
public class CustomerData {

	@Id
	private String custId;
	private String custName;
	private String gender;
	private int age;
	private long contactNo;
	private String emailId;
	private String employementStatus;
	
}
