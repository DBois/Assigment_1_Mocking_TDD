package dk.cphbusiness;

import dk.cphbusiness.banking.RealAccount;
import dk.cphbusiness.banking.Bank;
import dk.cphbusiness.banking.Customer;

public class AccountStub extends RealAccount {
    public AccountStub(Bank bank, Customer customer, String number) {
        super(bank, customer, number, new ClockDummy());
        customer.getAccountNumbers().add(number);
    }
}
