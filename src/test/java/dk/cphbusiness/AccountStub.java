package dk.cphbusiness;

import dk.cphbusiness.banking.Account;
import dk.cphbusiness.banking.Bank;
import dk.cphbusiness.banking.Customer;

public class AccountStub extends Account {

    public AccountStub(Bank bank, Customer customer, String number) {
        super(bank, customer, number);
    }


}
