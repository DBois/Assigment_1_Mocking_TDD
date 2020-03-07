package dk.cphbusiness;

import dk.cphbusiness.banking.Account;
import dk.cphbusiness.banking.Bank;
import dk.cphbusiness.banking.Customer;
import dk.cphbusiness.banking.RealMovement;

import java.util.List;

public class AccountDummy implements Account {
    @Override
    public Bank getBank() {
        return null;
    }

    @Override
    public Customer getCustomer() {
        return null;
    }

    @Override
    public String getNumber() {
        return null;
    }

    @Override
    public long getBalance() {
        return 0;
    }

    @Override
    public void transfer(long amount, Account target) {

    }

    @Override
    public void transfer(long amount, String targetNumber) {

    }

    @Override
    public void updateBalance(long amount) {

    }

    @Override
    public List<RealMovement> getMovements() {
        return null;
    }
}
