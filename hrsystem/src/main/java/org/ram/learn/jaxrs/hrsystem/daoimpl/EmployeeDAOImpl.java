package org.ram.learn.jaxrs.hrsystem.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.ram.learn.jaxrs.hrsystem.dao.EmployeeDAO;
import org.ram.learn.jaxrs.hrsystem.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	Connection conn;
	
	public EmployeeDAOImpl() {
		super();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:2521:xe","hr","hr");
		}
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@Override
	public List<Employee> getAllEmployees() {
		ArrayList<Employee> employees = new ArrayList<>();
		
		Statement stmt = null;
		String sqlStatement = "SELECT employee_id, first_name, last_name, hire_date, salary FROM employees";
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sqlStatement);
			
			while(rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt(1));
				employee.setFirstName(rs.getString(2));
				employee.setLastName(rs.getString(3));
				employee.setJoinDate(rs.getDate(4));
				employee.setSalary(rs.getFloat(5));
				employees.add(employee);
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return employees;
	}
	
	@Override
	public Employee getEmployee(int id) {
		int count = 0;
		Employee employee = new Employee();
		PreparedStatement stmt = null;
		String sqlStatement = "SELECT employee_id, first_name, last_name, hire_date, salary FROM employees WHERE employee_id = ?";
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sqlStatement);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while( rs.next() ) {
				employee.setId(rs.getInt(1));
				employee.setFirstName(rs.getString(2));
				employee.setLastName(rs.getString(3));
				employee.setJoinDate(rs.getDate(4));
				employee.setSalary(rs.getFloat(5));
				count ++;
			}
			
			System.out.println("Count: " + count);

			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return employee;

	}

	@Override
	public Employee updateEmployee(Employee employee) {
		
		Employee updateEmployee = new Employee();
		
		try {
			PreparedStatement stmt = null;
			String sqlStatement = null;
	
			sqlStatement = "UPDATE employees SET first_name = ?, last_name = ?, hire_date = ?, salary = ?";
		    sqlStatement = sqlStatement + " WHERE employee_id = ?";
		    
		    System.out.println("SQL Statement: " + sqlStatement);
		    
			stmt = conn.prepareStatement(sqlStatement);
			stmt.setString(1, employee.getFirstName());
			stmt.setString(2, employee.getLastName());
			stmt.setDate(3, new java.sql.Date(employee.getJoinDate().getTime()));
			stmt.setFloat(4, employee.getSalary());
			stmt.setInt(5, employee.getId());
			
			stmt.execute();

			stmt.close();
			
			updateEmployee = getEmployee(employee.getId());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			updateEmployee = null;
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return updateEmployee;
		
	}
	


}
