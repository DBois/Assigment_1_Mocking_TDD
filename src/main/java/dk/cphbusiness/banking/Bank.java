package dk.cphbusiness.banking;


import java.util.List;

public interface Bank {
    Account getAccount(String number);
    void registerAccount(RealAccount account);
    Customer getCustomer(String number);
    void registerCustomer(Customer customer);
    List<RealAccount> getAccounts(Customer customer);
    String getName();
}
