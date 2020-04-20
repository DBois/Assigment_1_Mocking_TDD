package dk.cphbusiness.banking.backend.datalayer;

import dk.cphbusiness.banking.backend.models.*;
import java.util.List;

public interface DataAccessObject {
    //Account
    RealAccount createAccount(Account account);
    RealAccount getAccount(String accountNumber) throws Exception;
    List<RealAccount> getAccountsFromCustomer(String CPR) throws Exception;
    List<RealAccount> getAccountsFromBank(String CVR);
    RealAccount updateAccount(Account Account);
    void deleteAccount(String accountNumber);
    RealMovement transfer(RealAccount acc1, RealAccount acc2, long timestamp) throws Exception;

    //Bank
    RealBank createBank(Bank bank);
    RealBank getBank(String CVR);
    List<RealBank> getBanks();
    RealBank updateBank(Bank bank);
    void deleteBank(String CVR);

    //Customer
    RealCustomer createCustomer(Customer customer);
    RealCustomer getCustomer(String CPR) throws Exception;
    List<RealCustomer> getCustomers();
    RealCustomer updateCustomer(RealCustomer customer) throws Exception;
    void deleteCustomer(String cpr) throws Exception;

    //Movement
    List<RealMovement> getMovements(String accountName) throws Exception;
}
