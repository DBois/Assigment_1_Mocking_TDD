package dk.cphbusiness.banking.backend;

import dk.cphbusiness.banking.backend.models.RealAccount;
import dk.cphbusiness.banking.backend.models.Bank;
import dk.cphbusiness.banking.backend.models.Customer;

public class AccountStub extends RealAccount {
    public AccountStub(Bank bank, Customer customer, String number) {
        super(bank, customer, number, new ClockDummy());
        customer.getAccountNumbers().add(number);
    }
}
