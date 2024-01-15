// Service interface defining methods for managing customer related operations

package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Customer;

public interface CustomerService {

    // Method to retrieve all customers
    List<Customer> getAllCustomers();

    // Method to retrieve a customer by their ID
    Customer getCustomerById(Long id);

    // Method to create or update a customer
    Customer createOrUpdateCustomer(Customer customer);

    // Method to delete a customer by their ID
    void deleteCustomer(Long id);

    // Method to synchronize customers from a remote API using a provided token
    void syncCustomersFromRemoteApi(String token);
    
    List<Customer> getAllCustomersWithSort(String sortBy);
}