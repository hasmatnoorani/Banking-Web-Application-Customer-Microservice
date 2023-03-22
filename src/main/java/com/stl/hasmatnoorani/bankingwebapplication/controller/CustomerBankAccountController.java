package com.stl.hasmatnoorani.bankingwebapplication.controller;

import com.stl.hasmatnoorani.bankingwebapplication.idgenerator.VariablesGenerator;
import com.stl.hasmatnoorani.bankingwebapplication.jwt.JwtUtil;
import com.stl.hasmatnoorani.bankingwebapplication.model.CustomerBankAccount;
import com.stl.hasmatnoorani.bankingwebapplication.repository.CustomerBankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/v1/customer-bank-account")
public class CustomerBankAccountController {

    @Autowired
    CustomerBankAccountRepository customerBankAccountRepository;

    @Autowired
    VariablesGenerator variablesGenerator;
    @Autowired
    JwtUtil jwtUtil;
    @PostMapping("/add/{customerEmail}")
    public String customer(@RequestBody CustomerBankAccount customerBankAccount, @PathVariable("customerEmail") String customerEmail) {
        try {
            customerBankAccount.setCustomerEmail(jwtUtil.getUsernameFromToken(customerEmail));
            customerBankAccount.setCustomerAccountStatus(0);
            customerBankAccountRepository.save(customerBankAccount);
            return "Customer Bank Account Added!";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Could not add Customer Bank Account!";
        }
    }
    @GetMapping("/all")
    public List<CustomerBankAccount> getAllCustomerBankAccounts(){
        List<CustomerBankAccount> customerBankAccount = customerBankAccountRepository.findAll();
        return customerBankAccount;
    }

    @GetMapping("/{email}")
    public CustomerBankAccount getCustomerBankAccountByEmail(@PathVariable("email") String email) {
        CustomerBankAccount customerBankAccountbyId = customerBankAccountRepository.findByEmailId(jwtUtil.getUsernameFromToken(email));
        return customerBankAccountbyId;
    }


    @GetMapping("/error")
    public String error(){
        return "Error";
    }
}
