package com.mss.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.mss.domain.Employee;
import com.mss.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	public Map<String, Object> saveEmployeeData(Employee employee) {
		Map<String, Object> response=new HashMap<>();
		employeeRepository.save(employee);
		response.put("message", "Employee details saved Successfully");
		return response;
	}

	public List getEmployeeData() {
		
		return employeeRepository.findAll();
	}

	 public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
	        String path = "C:/Users/ddevanaboyana/Documents/";
	        List<Employee> employees = employeeRepository.findAll();
	        //load file and compile it
	        File file = ResourceUtils.getFile("classpath:employees.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "Java Techie");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        if (reportFormat.equalsIgnoreCase("html")) {
	            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
	        }
	        if (reportFormat.equalsIgnoreCase("pdf")) {
	            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
	        }

	        return "report generated in path : " + path;
	    }
	
	

}
