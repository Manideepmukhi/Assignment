// Controller class for handling HTTP requests related to customers

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    // Autowired annotation for dependency injection of CustomerService
    @Autowired
    private CustomerService customerService;

    // Endpoint to retrieve all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Endpoint to retrieve a customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }
    
    @GetMapping("/sort")
    public List<Customer> getAllCustomersWithSort(@RequestParam(defaultValue = "uuid") String sortBy) {
        return customerService.getAllCustomersWithSort(sortBy);}

    // Endpoint to create a new customer
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createOrUpdateCustomer(customer);
    }

    // Endpoint to update an existing customer
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable(name = "id") Long id, @RequestBody Customer customer) {
        // Set the ID of the customer to update
        customer.setId(id);
        return customerService.createOrUpdateCustomer(customer);
    }

    // Endpoint to delete a customer by ID
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable(name = "id") Long id) {
        customerService.deleteCustomer(id);
    }

    // Endpoint to synchronize customers from a remote API using a provided token
    @PostMapping("/sync")
    public void syncCustomers(@RequestBody String token) {
        customerService.syncCustomersFromRemoteApi(token);
    }
}