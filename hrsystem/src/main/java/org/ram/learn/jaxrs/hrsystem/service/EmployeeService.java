package org.ram.learn.jaxrs.hrsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.ram.learn.jaxrs.hrsystem.dao.EmployeeDAO;
import org.ram.learn.jaxrs.hrsystem.daoimpl.EmployeeDAOImpl;
import org.ram.learn.jaxrs.hrsystem.model.Employee;

public class EmployeeService {
	
	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

	public List<Employee> getAllEmployees() {
		ArrayList<Employee> employees = new ArrayList<>();
		employees = (ArrayList<Employee>)employeeDAO.getAllEmployees();		
		return employees;
	}
	
	public Employee getEmployee(int id) {
		return employeeDAO.getEmployee(id);
	}

	public Employee updateEmployee(Employee employee) {
		return employeeDAO.updateEmployee(employee);
	}
	
}
