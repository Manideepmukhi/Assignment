package com.example.demo.service;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer createOrUpdateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void syncCustomersFromRemoteApi(String token) {
        // implemented all these 3 func. in this method
        // 1. Make an HTTP request to the remote API using the token.
        // 2. Receive a list of customers in the response.
        // 3. Iterate through the list and update or insert each customer into the database.

        // Example URL for fetching customers from the remote API
        String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";

        // Set the Authorization header with the provided token
        

        // Create a RestTemplate for making HTTP requests
        RestTemplate restTemplate = new RestTemplate();
         
        // Make the HTTP request to the remote API
        try {
        	System.out.println("Request Headers: " + createHeadersWithAuthorization(token));
        ResponseEntity<Customer[]> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                new HttpEntity<>(createHeadersWithAuthorization(token)),
                Customer[].class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            List<Customer> remoteCustomers = Arrays.asList(response.getBody());

            for (Customer remoteCustomer : remoteCustomers) {
                // Use findByUuid to check if the customer already exists in the database
                Customer existingCustomer = customerRepository.findByUuid(remoteCustomer.getUuid());

                if (existingCustomer != null) {
                    // Update existing customer
                    updateCustomer(existingCustomer, remoteCustomer);
                } else {
                    // Save new customer
                    customerRepository.save(remoteCustomer);
                }
            }
        } else {
            // Log or handle unexpected status code
            // For example, log the status code and response body
            System.out.println("Response body: " + Arrays.toString(response.getBody()));
        }}
        catch (Exception e) {
            // Log or handle the exception
            e.printStackTrace();
        }
    }

    private HttpHeaders createHeadersWithAuthorization(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return headers;
    }

    private void updateCustomer(Customer existingCustomer, Customer remoteCustomer) {
        // Update existing customer
        existingCustomer.setFirstName(remoteCustomer.getFirstName());
        existingCustomer.setLastName(remoteCustomer.getLastName());
        existingCustomer.setStreet(remoteCustomer.getStreet());
        existingCustomer.setAddress(remoteCustomer.getAddress());
        existingCustomer.setCity(remoteCustomer.getCity());
        existingCustomer.setState(remoteCustomer.getState());
        existingCustomer.setEmail(remoteCustomer.getEmail());
        existingCustomer.setPhone(remoteCustomer.getPhone());

        // Save the updated customer
        customerRepository.save(existingCustomer);
    }
}