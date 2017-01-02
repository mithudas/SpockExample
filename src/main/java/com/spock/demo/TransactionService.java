package com.spock.demo;


public class TransactionService {
    EmailService emailService ;

    public String doTransfer(Account sourceAccount, Account destinationAccount, Double amount) throws InsufficientFundsException {
        String message;
        if (amount > 10000)
            throw new InsufficientFundsException(amount);


        if (!ifExistsAccount(sourceAccount))
            message = "You don't have any account";
        else if (!ifExistsAccount(destinationAccount))
            message = "A/c Payee don't have any account";
        else if (sourceAccount.accountNumber.equals(destinationAccount.accountNumber))
            message = "Source and Destination accounts cannot be same!!";
        else if (amount < 100)
            message = "Minimum Rs 100 is required for a transaction.";
        else{
            emailService = new EmailService();
            emailService.sendMail(amount);
            message = "Rs. " + amount + " has been transferred from A/c " + sourceAccount.accountNumber + " to " + "Destination A/c " + destinationAccount.accountNumber;
        }
        return message;
    }

    private Boolean ifExistsAccount(Account account) {
        return account.exists();
    }
}
