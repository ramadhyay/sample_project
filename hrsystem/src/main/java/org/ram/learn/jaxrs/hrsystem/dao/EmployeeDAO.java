package org.ram.learn.jaxrs.hrsystem.dao;

import java.util.List;

import org.ram.learn.jaxrs.hrsystem.model.Employee;

public interface EmployeeDAO {
	public List<Employee> getAllEmployees();
	public Employee getEmployee(int id);
	public Employee updateEmployee(Employee employee);

}
