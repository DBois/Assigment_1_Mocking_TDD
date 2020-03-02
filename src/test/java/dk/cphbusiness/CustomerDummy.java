package dk.cphbusiness;

import dk.cphbusiness.banking.Account;
import dk.cphbusiness.banking.Customer;

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
    public List<String> getAccountNames() {
        return null;
    }

    @Override
    public void addAccountName(String accountName) {

    }
}
