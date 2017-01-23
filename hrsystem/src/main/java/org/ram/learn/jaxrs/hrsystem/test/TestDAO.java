package org.ram.learn.jaxrs.hrsystem.test;

import java.util.ArrayList;

import org.ram.learn.jaxrs.hrsystem.dao.EmployeeDAO;
import org.ram.learn.jaxrs.hrsystem.daoimpl.EmployeeDAOImpl;
import org.ram.learn.jaxrs.hrsystem.model.Employee;

public class TestDAO {
	public static void main(String[] args) {
		EmployeeDAO employeeDAO = new EmployeeDAOImpl();
		ArrayList<Employee> employees = (ArrayList)employeeDAO.getAllEmployees();
		for(Employee nextEmployee:employees){
			System.out.println("First Name: " + nextEmployee.getFirstName());
		}
	}
}
