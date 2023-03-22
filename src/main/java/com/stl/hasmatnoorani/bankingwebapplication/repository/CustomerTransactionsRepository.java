package com.stl.hasmatnoorani.bankingwebapplication.repository;

import com.stl.hasmatnoorani.bankingwebapplication.model.CustomerTransactions;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerTransactionsRepository extends JpaRepository<CustomerTransactions, String> {
    @Query(value = "Select * from customer_transactions where sender_account_id=? and transaction_date_time >= ? and transaction_date_time<=?", nativeQuery = true)
    public List<CustomerTransactions> findByEmailId(String email, Date startDate, Date endDate);

    @Query(value = "Select * from customer_transactions where recipient_account_number=? and transaction_date_time >= ? and transaction_date_time<=?", nativeQuery = true)
    public List<CustomerTransactions> findByRecipientAccountNumber(String recipientAccountNumber, Date startDate, Date endDate);
}
