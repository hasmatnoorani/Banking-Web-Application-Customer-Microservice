package com.stl.hasmatnoorani.bankingwebapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Entity
@Table(name="customer_transactions")
public class CustomerTransactions {
    @Id
    @Column(name="transaction_id")
    private String transactionId;

    @Column(name="recipient_account_number")
    private String recipientAccountNumber;

    @Column(name="recipient_name")
    private String recipientName;

    @Column(name="recipient_nickname")
    private String  recipientNickname;

    @Column(name="recipient_bank_ifsc_code")
    private String recipientBankIfscCode;

    @Column(name="transaction_amount")
    private Double transactionAmount;

    @Column(name="transaction_date_time")
    private Date transactionDate;

    @Column(name="sender_account_id")
    private String senderAccountId;

    @Column(name="sender_comment")
    private String senderComment;

    @Column(name = "sender_account_number")
    private String senderAccountNumber;
    @Column(name = "sender_name")
    private String senderName;
    public CustomerTransactions() {
    }

    public CustomerTransactions(String transactionId, String recipientAccountNumber, String recipientName, String recipientNickname, String recipientBankIfscCode, Double transactionAmount, Date transactionDate, String senderAccountId, String senderComment, String senderAccountNumber, String senderName) {
        this.transactionId = transactionId;
        this.recipientAccountNumber = recipientAccountNumber;
        this.recipientName = recipientName;
        this.recipientNickname = recipientNickname;
        this.recipientBankIfscCode = recipientBankIfscCode;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.senderAccountId = senderAccountId;
        this.senderComment = senderComment;
        this.senderAccountNumber = senderAccountNumber;
        this.senderName = senderName;
    }

    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getRecipientAccountNumber() {
        return recipientAccountNumber;
    }

    public void setRecipientAccountNumber(String recipientAccountNumber) {
        this.recipientAccountNumber = recipientAccountNumber;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientNickname() {
        return recipientNickname;
    }

    public void setRecipientNickname(String recipientNickname) {
        this.recipientNickname = recipientNickname;
    }

    public String getRecipientBankIfscCode() {
        return recipientBankIfscCode;
    }

    public void setRecipientBankIfscCode(String recipientBankIfscCode) {
        this.recipientBankIfscCode = recipientBankIfscCode;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(String senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public String getSenderComment() {
        return senderComment;
    }

    public void setSenderComment(String senderComment) {
        this.senderComment = senderComment;
    }

    @Override
    public String toString() {
        return "CustomerTransactions{" +
                "transactionId='" + transactionId + '\'' +
                ", recipientAccountNumber='" + recipientAccountNumber + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", recipientNickname='" + recipientNickname + '\'' +
                ", recipientBankIfscCode='" + recipientBankIfscCode + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionDate=" + transactionDate +
                ", senderAccountId='" + senderAccountId + '\'' +
                ", senderComment='" + senderComment + '\'' +
                ", senderAccountNumber='" + senderAccountNumber + '\'' +
                ", senderName='" + senderName + '\'' +
                '}';
    }
}
