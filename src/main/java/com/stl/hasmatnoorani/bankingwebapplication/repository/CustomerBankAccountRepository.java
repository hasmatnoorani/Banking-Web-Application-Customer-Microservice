package com.stl.hasmatnoorani.bankingwebapplication.repository;

import com.stl.hasmatnoorani.bankingwebapplication.model.CustomerBankAccount;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerBankAccountRepository extends JpaRepository<CustomerBankAccount, String> {
    @Query(value = "Select * from customer_bank_accounts where customer_email=?", nativeQuery = true)
    public CustomerBankAccount findByEmailId(String email);

    @Query(value = "Select * from customer_bank_accounts where customer_bank_account_number=?", nativeQuery = true)
    public CustomerBankAccount findByAccountNumber(String accountNumber);


}
