package com.stl.hasmatnoorani.bankingwebapplication.repository;

import com.stl.hasmatnoorani.bankingwebapplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
}
