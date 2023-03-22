package com.stl.hasmatnoorani.bankingwebapplication.controller;

import com.stl.hasmatnoorani.bankingwebapplication.idgenerator.VariablesGenerator;
import com.stl.hasmatnoorani.bankingwebapplication.jwt.JwtUtil;
import com.stl.hasmatnoorani.bankingwebapplication.model.Customer;
import com.stl.hasmatnoorani.bankingwebapplication.model.CustomerBankAccount;
import com.stl.hasmatnoorani.bankingwebapplication.model.CustomerTransactions;
import com.stl.hasmatnoorani.bankingwebapplication.repository.CustomerBankAccountRepository;
import com.stl.hasmatnoorani.bankingwebapplication.repository.CustomerRepository;
import com.stl.hasmatnoorani.bankingwebapplication.repository.CustomerTransactionsRepository;
import com.stl.hasmatnoorani.bankingwebapplication.security.CustomerLoginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/v1/customer/transactions")
public class CustomerTransactionsController {
    @Autowired
    CustomerTransactionsRepository customerTransactionsRepository;

    @Autowired
    CustomerTransactions customerTransactions;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    VariablesGenerator variablesGenerator;

    @Autowired
    CustomerBankAccountRepository customerBankAccountRepository;

    @PostMapping("/initiate-transaction/{customerEmail}")
    public String customer(@RequestBody CustomerTransactions customerTransactions, @PathVariable("customerEmail") String customerEmail) {
        try {
            CustomerBankAccount customerBankAccount2=customerBankAccountRepository.findByEmailId(jwtUtil.getUsernameFromToken(customerEmail));
            customerTransactions.setSenderAccountNumber(customerBankAccount2.getCustomerBankAccountNumber());
            customerTransactions.setSenderName(customerBankAccount2.getCustomerName());
            customerTransactions.setSenderAccountId(jwtUtil.getUsernameFromToken(customerEmail));
            customerTransactions.setTransactionDate(new Date());
            customerTransactions.setTransactionId(variablesGenerator.transactionId());
            customerTransactionsRepository.save(customerTransactions);

            CustomerBankAccount customerBankAccount=customerBankAccountRepository.findByEmailId(jwtUtil.getUsernameFromToken(customerEmail));
            customerBankAccount.setCustomerBalance(customerBankAccount.getCustomerBalance()-customerTransactions.getTransactionAmount());
            customerBankAccountRepository.save(customerBankAccount);

            CustomerBankAccount customerBankAccount1=customerBankAccountRepository.findByAccountNumber(customerTransactions.getRecipientAccountNumber());
            customerBankAccount1.setCustomerBalance(customerBankAccount1.getCustomerBalance()+customerTransactions.getTransactionAmount());
            customerBankAccountRepository.save(customerBankAccount1);

            return "Transaction Successful!";

        }
        catch (Exception e){
            e.printStackTrace();
            return "Transaction Failed!";
        }
    }
    @GetMapping("/get-all-transactions")
    public List<CustomerTransactions> getAllCustomerTransactions(){
        List<CustomerTransactions> customerTransactions = customerTransactionsRepository.findAll();
        return customerTransactions;
    }

    //Fetching Customers By ID
    @GetMapping("get-transaction-by-customer-account-id/{email}/{startDate}/{endDate}")
    public List<CustomerTransactions> getCustomerTransactionByAccountId(@PathVariable("email") String email, @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return customerTransactionsRepository.findByEmailId(jwtUtil.getUsernameFromToken(email),startDate,endDate);
    }
    @GetMapping("get-transaction-by-recipent-account-number/{accountNo}/{startDate}/{endDate}")
    public List<CustomerTransactions> getCustomerTransactionByAccountNumber(@PathVariable("accountNo") String accountNo,@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,@PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return customerTransactionsRepository.findByRecipientAccountNumber(accountNo,startDate,endDate);
    }
}
