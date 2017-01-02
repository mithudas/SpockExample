package com.spock.demo

import spock.lang.Specification
import spock.lang.Unroll


class TransactionServiceTest extends Specification {
    @Unroll
    def "test doTransfer for validations"() {
        given:
        TransactionService transactionService = new TransactionService();

        when:
        String result = transactionService.doTransfer(sourceAccount, destinationAccount, amount)

        then:
        result.equals(expectedMessage);

        where:
        description                                     | sourceAccount     | destinationAccount | amount | expectedMessage
        "when Source Account doesn't exist"             | new Account(0000) | new Account(2222)  | 500.00 | "You don't have any account"
        "when Destination Account doesn't exist"        | new Account(1111) | new Account(0000)  | 500.00 | "A/c Payee don't have any account"
        "when Source and Destination Accounts are same" | new Account(1111) | new Account(1111)  | 500.00 | "Source and Destination accounts cannot be same!!"
        "when transferred amount is less than Rs 100"   | new Account(1111) | new Account(2222)  | 50.00  | "Minimum Rs 100 is required for a transaction."
        "when transaction is successful"                | new Account(1111) | new Account(2222)  | 500.00 | "Rs. 500.0 has been transferred from A/c 1111 to Destination A/c 2222"
    }

    def "test doTransfer for exception"() {
        given:
        TransactionService transactionService = new TransactionService();
        Account sourceAccount = new Account(1111);
        Account destinationAccount = new Account(2222);

        when:
        transactionService.doTransfer(sourceAccount, destinationAccount, 20000)

        then:
        thrown(InsufficientFundsException)
    }

    def "test doTransfer for email"(){
        given:
        TransactionService transactionService = new TransactionService();
        Account sourceAccount = new Account(1111);
        Account destinationAccount = new Account(2222);
        EmailService emailService = new EmailService();


        when:
        transactionService.doTransfer(sourceAccount, destinationAccount, 2000);
        emailService.sendMail() >> { return "mail sent" }

        then:
        1*emailService.sendMail()

    }
}
