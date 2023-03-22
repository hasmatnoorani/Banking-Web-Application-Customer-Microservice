package com.stl.hasmatnoorani.bankingwebapplication.controller;

import com.stl.hasmatnoorani.bankingwebapplication.jwt.JwtUtil;
import com.stl.hasmatnoorani.bankingwebapplication.model.Customer;
import com.stl.hasmatnoorani.bankingwebapplication.repository.CustomerRepository;
import com.stl.hasmatnoorani.bankingwebapplication.security.CustomerLoginDetails;
import com.stl.hasmatnoorani.bankingwebapplication.security.CustomerLoginDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/v1/customer/auth")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtils;

    @Autowired
    CustomerLoginDetailsService customerLoginDetailsService;

    @PostMapping("/add")
    public String customer(@RequestBody Customer customer) {
        try {
            customer.setDateCustomerJoined(new Date());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encPass = encoder.encode(customer.getCustomerPassword());
            customer.setCustomerPassword(encPass);
            customerRepository.save(customer);
            return "Customer Added!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Could not add Customer!";
        }
    }


    @PostMapping("/authenticate")
    public String authenticate(@RequestBody Customer customer) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(customer.getCustomerEmail(), customer.getCustomerPassword()));
            if (authentication.isAuthenticated()) {
                CustomerLoginDetails customerLoginDetails = (CustomerLoginDetails) customerLoginDetailsService.loadUserByUsername(customer.getCustomerEmail());
                String token = jwtUtils.generateJwtToken(customerLoginDetails);
                return token;
            } else {
                return "Authentication Failed!";
            }
        } catch (Exception e) {
            return "Authentication Failed! Check username & password.";
        }
    }

}
