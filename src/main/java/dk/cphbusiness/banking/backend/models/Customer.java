package dk.cphbusiness.banking.backend.models;

import java.util.List;

public interface Customer {
    String getCpr();
    String getName();
    List<String> getAccountNumbers();
    void addAccountNumber(String accountNumber);

}
