package dk.cphbusiness.banking.backend.doubles;

import dk.cphbusiness.banking.backend.models.RealAccount;
import dk.cphbusiness.banking.backend.models.Bank;
import dk.cphbusiness.banking.backend.models.Customer;

public class AccountStub extends RealAccount {
    public AccountStub(Bank bank, Customer customer, String number) {
        super(bank, customer, number);
        customer.getAccountNumbers().add(number);
    }
}
