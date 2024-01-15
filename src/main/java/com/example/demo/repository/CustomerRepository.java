// Repository interface for accessing and managing Customer entities in the database

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Customer;

// JpaRepository provides CRUD operations for Customer entities with Long as the type of the primary key
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Custom query method to find a customer by their unique UUID
    Customer findByUuid(String uuid);
}