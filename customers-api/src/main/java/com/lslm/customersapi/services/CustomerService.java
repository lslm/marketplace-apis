package com.lslm.customersapi.services;

import com.lslm.customersapi.entities.Customer;
import com.lslm.customersapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer find(UUID id) {
        return customerRepository.findById(id).orElse(null);
    }
}
