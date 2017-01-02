package com.spock.demo;


public class EmailService {
    public String sendMail(Double amount){
        String emailMessage =  "Rs." + amount + " has been Credited into your account";
        return emailMessage;
    }
}
