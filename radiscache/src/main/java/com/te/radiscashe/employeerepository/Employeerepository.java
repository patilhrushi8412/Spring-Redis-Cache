package com.te.radiscashe.employeerepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.radiscashe.employeeentity.Employee;

@Repository
public interface Employeerepository extends JpaRepository<Employee, Integer> {

	
	Optional<Employee> findByEmployeeId(String employeeId);

}
