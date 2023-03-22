package com.stl.hasmatnoorani.bankingwebapplication.controller;

import com.stl.hasmatnoorani.bankingwebapplication.jwt.JwtUtil;
import com.stl.hasmatnoorani.bankingwebapplication.model.Customer;
import com.stl.hasmatnoorani.bankingwebapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/v1/customer")
public class CustomerDetailsController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    JwtUtil jwtUtil;
    @GetMapping("/all")
    public List<Customer> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    //Fetching Customers By ID
    @GetMapping("/{email}")
    public Optional<Customer> getCustomerByEmail(@PathVariable("email") String email) {
        return customerRepository.findById(jwtUtil.getUsernameFromToken(email));
    }

}
