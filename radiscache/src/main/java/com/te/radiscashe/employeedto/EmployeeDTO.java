package com.te.radiscashe.employeedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

	private String employeeId;
	private String employeeName;
	private String employeeEmail;
	private String designation;
	private long ctc;
	private long mobileNo;
}
