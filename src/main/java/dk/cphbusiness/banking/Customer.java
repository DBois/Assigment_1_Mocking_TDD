package dk.cphbusiness.banking;

import java.util.List;

public interface Customer {
    void transfer(long amount, Account account, Customer target);

    String getCpr();
    String getName();
    List<String> getAccountNumbers();

    void addAccountNumber(String accountNumber);

    Bank getBank();
}
