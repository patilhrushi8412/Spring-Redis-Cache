package com.te.radiscashe.employeeservice;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.te.radiscashe.employeedto.EmployeeDTO;
import com.te.radiscashe.employeeentity.Employee;
import com.te.radiscashe.employeerepository.Employeerepository;
import com.te.radiscashe.exception.CustomException;

@EnableCaching
@Service
public class EmployeeServiceImpl implements EmployeeServices {

	@Autowired
	private Employeerepository repo;

	@Override
	public Employee addEmployee(EmployeeDTO empDto) {
		try {
			LocalDate d = LocalDate.now();
			if (empDto == null) {
				throw new CustomException("Enter Data");
			}
			Employee emp = new Employee();
			BeanUtils.copyProperties(empDto, emp);
			emp.setEmployeeId("TYC0" + d.getMonthValue() + "0" + d.getYear() + "0" + (repo.findAll().size() + 1));
			Employee save = repo.save(emp);
			return save;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Cacheable(key = "#employeeId", value = "Employee")
	public Employee getemployee(String employeeId) {
		try {
			Optional<Employee> emp = repo.findByEmployeeId(employeeId);

			if (!emp.isPresent()) {
				throw new CustomException("Employee Is Not Present On This Id");
			} else {
				return emp.get();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@CacheEvict(key = "#employeeId", value = "Employee")
	public void deleteemployee(String employeeId) {
		try {
			Optional<Employee> emp = repo.findByEmployeeId(employeeId);
			if (!emp.isPresent()) {
				throw new CustomException("Employee Is Not Present On This Id");
			} else {
				repo.delete(emp.get());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Cacheable(value = "Employee")
	public List<Employee> getAllemployee() {
		try {
			List<Employee> empList = repo.findAll();
			if (empList.isEmpty()) {
				throw new CustomException("No One Employee Is Present");
			} else {
				return empList;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@CachePut(value = "Employee")
	public Employee updateEmployee(EmployeeDTO empDto) {
		try {
			Optional<Employee> employee = repo.findByEmployeeId(empDto.getEmployeeId());
			if (!employee.isPresent()) {
				throw new CustomException("Which Employee You Want To Update Is Not Present");
			} else {
				BeanUtils.copyProperties(empDto, employee.get());
				Employee save = repo.save(employee.get());
				return save;
			}

		} catch (Exception e) {
			throw e;
		}
	}

}
