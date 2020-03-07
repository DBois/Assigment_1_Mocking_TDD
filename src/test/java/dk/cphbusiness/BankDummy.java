package dk.cphbusiness;

import dk.cphbusiness.banking.RealAccount;
import dk.cphbusiness.banking.Bank;
import dk.cphbusiness.banking.Customer;

import java.util.List;

public class BankDummy implements Bank {
    @Override
    public RealAccount getAccount(String number) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void registerAccount(RealAccount account) {

    }

    @Override
    public Customer getCustomer(String number) {
        return null;
    }

    @Override
    public void registerCustomer(Customer customer) {

    }

    @Override
    public List<RealAccount> getAccounts(Customer customer) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
