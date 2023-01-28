package com.webservice.ws.controller;

import com.webservice.ws.model.*;

import java.util.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class Controller {
	
	Map<String, RestEmp> allEmp = new HashMap<>();
	
	
	@GetMapping
	public Collection<RestEmp> getM() {
		return allEmp.values();
	}
	@PostMapping
	public String postM(@RequestBody RestEmp empdetails) {
		RestEmp addVal = new RestEmp();
		addVal.setEmpid(empdetails.getEmpid());
		addVal.setEmpname(empdetails.getEmpname());
		addVal.setEmail(empdetails.getEmail());
		allEmp.put(empdetails.getEmpid(), addVal);
		return "employee details added";
	}
	@PutMapping(path="/{x}/{empid}")
	public String putM(@PathVariable String x,@PathVariable String empid,@RequestBody RestEmp empdetails) {
		if(x.equals("allowedit") ){
		if(allEmp.containsKey(empid)) {
			RestEmp addVal = new RestEmp();
			addVal.setEmpid(empdetails.getEmpid());
			addVal.setEmpname(empdetails.getEmpname());
			addVal.setEmail(empdetails.getEmail());
			allEmp.put(empid, addVal);
			return "details updated";
		}
		return "emp id not found";
		}
		return "unauthorized user";
	}
	@DeleteMapping(path="/{y}/{empid}")
	public String deleteM(@PathVariable String y,@PathVariable String empid) {
		if(y.equals("allowdelete")) {
		if(allEmp.containsKey(empid)) {
			allEmp.remove(empid);
			return "employee details deleted successfully";			
		}
		return "emp id not found";
		}
		return "unauthorized user";
	}

}
