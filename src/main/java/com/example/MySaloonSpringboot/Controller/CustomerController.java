package com.example.MySaloonSpringboot.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.MySaloonSpringboot.Model.Customer;
import com.example.MySaloonSpringboot.Repository.CustomerRepository;
import com.example.MySaloonSpringboot.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // get all customers
    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // post customer
    @PostMapping("/")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // update customer
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
        Customer cust = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));
        cust.setName(customer.getName());
        cust.setEmail(customer.getEmail());
        cust.setPhone(customer.getPhone());

        Customer cust2 = customerRepository.save(cust);
        return ResponseEntity.ok(cust2);
    }

    // get customer by id
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
        Customer cust = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));
        return ResponseEntity.ok(cust);
    }

    // delete customer
    @DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable Long id){
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		customerRepository.delete(customer);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
    
}
