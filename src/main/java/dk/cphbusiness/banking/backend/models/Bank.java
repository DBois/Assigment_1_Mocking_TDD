package dk.cphbusiness.banking.backend.models;


import java.util.List;

public interface Bank {
    Account getAccount(String number);
    void registerAccount(Account account);
    Customer getCustomer(String number);
    void registerCustomer(Customer customer);
    List<Account> getAccounts(Customer customer);
    String getName();
}
