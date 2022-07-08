package com.example.MySaloonSpringboot.Repository;

import com.example.MySaloonSpringboot.Model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
    
}
