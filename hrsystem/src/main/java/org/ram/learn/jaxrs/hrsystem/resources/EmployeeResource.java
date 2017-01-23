package org.ram.learn.jaxrs.hrsystem.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ram.learn.jaxrs.hrsystem.model.Employee;
import org.ram.learn.jaxrs.hrsystem.service.EmployeeService;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {
	private EmployeeService employeeService = new EmployeeService();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getEmployeesTest() {
		return "Employee Test  !!!";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GET
	@Path("/{employeeID}")
	public Employee getEmployee(@PathParam("employeeID") int id) {
		return employeeService.getEmployee(id);
	}
	
	@PUT
	@Path("/{employeeID}")
	public Employee updateEmployee(@PathParam("employeeID") int id, Employee employee) {
		employee.setId(id);
		return employeeService.updateEmployee(employee);
	}

}
