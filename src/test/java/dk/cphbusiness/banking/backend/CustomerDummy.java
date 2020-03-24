package dk.cphbusiness.banking.backend;

import dk.cphbusiness.banking.backend.models.Account;
import dk.cphbusiness.banking.backend.models.Bank;
import dk.cphbusiness.banking.backend.models.Customer;

import java.util.List;

public class CustomerDummy implements Customer {

    @Override
    public void transfer(long amount, Account account, Customer target) {

    }

    @Override
    public String getCpr() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<String> getAccountNumbers() {
        return null;
    }

    @Override
    public void addAccountNumber(String accountNumber) {

    }

    @Override
    public Bank getBank() {
        return null;
    }
}
