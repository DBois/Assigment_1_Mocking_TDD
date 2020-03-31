package dk.cphbusiness.banking.backend.datalayer;

import dk.cphbusiness.banking.backend.models.Account;
import dk.cphbusiness.banking.backend.models.Bank;
import dk.cphbusiness.banking.backend.models.Customer;
import dk.cphbusiness.banking.contract.AccountManager;
import dk.cphbusiness.banking.contract.BankManager;
import dk.cphbusiness.banking.contract.CustomerManager;
import dk.cphbusiness.banking.contract.MovementManager;
import net.sourceforge.plantuml.graph.Move;

import static dk.cphbusiness.banking.contract.AccountManager.*;
import static dk.cphbusiness.banking.contract.BankManager.*;
import static dk.cphbusiness.banking.contract.MovementManager.*;
import static dk.cphbusiness.banking.contract.CustomerManager.*;

import java.util.List;
import java.util.Map;

public interface DataAccessObject {
    //Account
    AccountDetail createAccount(Account account);
    AccountDetail getAccount(String accountNumber);
    List<AccountSummary> getAccountsFromCustomer(String CPR);
    List<AccountSummary> getAccountsFromBank(String CVR);
    AccountDetail updateAccount(Account Account);
    void deleteAccount(String accountNumber);

    //Bank
    BankDetail createBank(Bank bank);
    BankDetail getBank(String CVR);
    List<BankSummary> getBanks();
    BankDetail updateBank(Bank bank);
    void deleteBank(String CVR);

    //Customer
    CustomerDetail createCustomer(Customer customer);
    CustomerDetail getCustomer(String CPR);
    List<CustomerSummary> getCustomers();
    CustomerDetail updateCustomer();
    void deleteCustomer();

    //Movement
    List<MovementDetail> getMovements(String accountName);


}
