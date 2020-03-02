package dk.cphbusiness;

import dk.cphbusiness.banking.Account;
import dk.cphbusiness.banking.Bank;

public class BankDummy implements Bank {
    @Override
    public Account getAccount(String number) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
        return null;
    }
}
