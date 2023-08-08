package com.mss.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mss.domain.Employee;
import com.mss.service.EmployeeService;

import net.sf.jasperreports.engine.JRException;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/employee")
	public Map<String,Object> saveEmployeeData(@RequestBody Employee employee){
		return employeeService.saveEmployeeData(employee);
	}
	
	@GetMapping("/employee")
	public List getEmployeeData() {
		return employeeService.getEmployeeData();
	}
	
	
	 @GetMapping("/report/{format}")
	    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
	        return employeeService.exportReport(format);
	    }
}
