package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.Department;
import com.spring.service.DepartmentService;

@RestController
@RequestMapping("/custody")
public class DepartmentController {

	
	// post and put  in postman method will not work due to CSRF
	
	
	@Autowired
	private DepartmentService departmentService;
	

	// Save operation
	//@PostMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.
	//annotation for mapping HTTP POST requests onto specific handler methods.
	//@RequestBody annotation allows us to retrieve the request's body
	///@RequestBody annotation maps the HttpRequest body to a transfer or domain object, enabling automatic deserialization of the inbound HttpRequest body onto a Java object
	@PostMapping("/save")
	public Department saveDepartment(@RequestBody Department department) {
		System.out.println("inside save departments");
		return departmentService.saveDepartment(department);
	}

	// Read operation
	@GetMapping("/departments")
	public List<Department> fetchDepartmentList() {
		return departmentService.fetchDepartmentList();
	}

	// Update operation
	//@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public Department updateDepartment(@RequestBody Department department, @PathVariable("id") Long departmentId) {
		return departmentService.updateDepartment(department, departmentId);
	}

	// Delete operation
	@DeleteMapping("/delete/{id}")
	public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
		departmentService.deleteDepartmentById(departmentId);
		return "Deleted Successfully";
	}

}
