package dk.cphbusiness.banking.backend.models;

import java.util.List;

public interface Customer {
    void transfer(long amount, Account account, Customer target, long timeStamp);
    String getCpr();
    String getName();
    List<String> getAccountNumbers();
    void addAccountNumber(String accountNumber);
    Bank getBank();

}
