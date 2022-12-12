package com.te.radiscashe.employeeservice;

import java.util.List;

import com.te.radiscashe.employeedto.EmployeeDTO;
import com.te.radiscashe.employeeentity.Employee;

public interface EmployeeServices {

	Employee addEmployee(EmployeeDTO empDto);

	Employee getemployee(String employeeId);

	void deleteemployee(String employeeId);

	List<Employee> getAllemployee();

	Employee updateEmployee(EmployeeDTO empDto);

}
