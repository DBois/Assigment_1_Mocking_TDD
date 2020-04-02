package dk.cphbusiness.banking.backend.doubles;

import dk.cphbusiness.banking.backend.models.*;

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
    public void transfer(long amount, Account target, long timeStamp) {

    }

    @Override
    public void transfer(long amount, String targetNumber, long timeStamp) {

    }

    @Override
    public void updateBalance(long amount) {

    }

    @Override
    public List<Movement> getMovements() {
        return null;
    }
}
