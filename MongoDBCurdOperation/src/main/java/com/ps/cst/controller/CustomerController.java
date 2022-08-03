package com.ps.cst.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ps.cst.customer.CustomerData;
import com.ps.cst.repository.CustomerRespository;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/mongo")
public class CustomerController {

	@Autowired
	private CustomerRespository customerRespository; 
	
	@GetMapping("/get")
	public ResponseEntity<String> getResult(){
		
		return new ResponseEntity<String>("Result Success",HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<CustomerData> saveCustomer(@RequestBody CustomerData customerData) {
		    
		     String result="";
		  
			  customerData=customerRespository.save(customerData);
		     
		  
		  if(customerData.getCustId()!=null)
			  return new ResponseEntity<CustomerData>(customerData,HttpStatus.OK);
		  else 
			 return  new ResponseEntity<CustomerData>(customerData,HttpStatus.OK);
		
		  
		
	}
	
	@PutMapping("/update/{custId}")
	public ResponseEntity<CustomerData> updateCustomer(@PathVariable("custId") String custId, @RequestBody CustomerData customerData){
		System.out.println("custId "+custId);
		Optional<CustomerData> optionalCustomerData =customerRespository.findByCustId(custId);
		   System.out.println(optionalCustomerData);
		    if(optionalCustomerData.isPresent()) {
		    	CustomerData customerData2=optionalCustomerData.get();
		    	System.out.println("customerData2");
		    	customerData.setCustId(customerData2.getCustId());
		    	customerRespository.save(customerData);
		    }
		
		return new ResponseEntity<CustomerData>(customerData,HttpStatus.OK);
	}
	
	@GetMapping("/find-all")
	public ResponseEntity<List<CustomerData>> fetchAllCustomerData(){
		
		List<CustomerData> listCustomer=customerRespository.findAll();
		
		
		return new ResponseEntity<List<CustomerData>>(listCustomer,HttpStatus.OK);
	}
	
	@GetMapping("/find-customer/{id}")
	public ResponseEntity<CustomerData> findCustomerById(@PathVariable("id")String custId){
		Optional<CustomerData> optional=customerRespository.findById(custId);
		   System.out.println(optional.isEmpty());
		return new ResponseEntity<CustomerData>(optional.get(),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-customer/{id}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable("id")String custId){
		customerRespository.deleteById(custId);
		   
		return new ResponseEntity<String>("Customer Deleted: "+custId,HttpStatus.OK);
	}
}
