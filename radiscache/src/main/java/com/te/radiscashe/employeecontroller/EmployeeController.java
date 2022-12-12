package com.te.radiscashe.employeecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.radiscashe.employeedto.EmployeeDTO;
import com.te.radiscashe.employeeentity.Employee;
import com.te.radiscashe.employeeservice.EmployeeServices;
import com.te.radiscashe.response.Response;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeServices service;

	@PostMapping("/addEmployee")
	public ResponseEntity<Response> addEmployee(@RequestBody EmployeeDTO empDto) {
		Employee emp = service.addEmployee(empDto);
		return new ResponseEntity<Response>(new Response(false, "Employee Added Succesfully", emp), HttpStatus.OK);
	}

	@GetMapping("/getEmployee/{employeeId}")
	public ResponseEntity<Response> getEmployee(@PathVariable String employeeId) {
		Employee emp = service.getemployee(employeeId);
		return new ResponseEntity<Response>(new Response(false, "Getting Employee Detail Succesfully", emp),
				HttpStatus.OK);
	}

	@DeleteMapping("/deleteEmployee/{employeeId}")
	public ResponseEntity<Response> deleteEmployee(@PathVariable String employeeId) {
		service.deleteemployee(employeeId);
		return new ResponseEntity<Response>(new Response(false, "Employee Deleted Succesfully", null), HttpStatus.OK);
	}

	@GetMapping("/getAllEmployee")
	public ResponseEntity<Response> getAllEmployee() {
		List<Employee> all = service.getAllemployee();
		return new ResponseEntity<Response>(new Response(false, "All Employee Details", all), HttpStatus.OK);
	}

	@PutMapping("/updateEmployee")
	public ResponseEntity<Response> updateEmployee(@RequestBody EmployeeDTO empDto) {
		Employee emp = service.updateEmployee(empDto);	
		return new ResponseEntity<Response>(new Response(false, "Employee Updated Succesfully", emp), HttpStatus.OK);
	}
}
