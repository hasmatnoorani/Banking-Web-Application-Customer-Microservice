package com.stl.hasmatnoorani.bankingwebapplication.security;

import com.stl.hasmatnoorani.bankingwebapplication.model.Customer;
import com.stl.hasmatnoorani.bankingwebapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerLoginDetailsService implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findById(username).get();
        return new CustomerLoginDetails(customer);
    }
}
