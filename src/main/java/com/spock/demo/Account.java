package com.spock.demo;


public class Account {
    Long accountNumber;
    Double balance;

    public Double getBalance(Long accountNumber) {
        return this.balance;
    }

    public Account(Long accountNumber, Double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Account(Long accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.00;
    }

    public Boolean exists() {
        return this.accountNumber >= 1000 && this.accountNumber < 5000;

    }
}
