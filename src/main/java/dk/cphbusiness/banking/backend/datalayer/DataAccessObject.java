package dk.cphbusiness.banking.backend.datalayer;

import dk.cphbusiness.banking.backend.models.*;
import dk.cphbusiness.banking.contract.AccountManager;
import dk.cphbusiness.banking.contract.BankManager;
import dk.cphbusiness.banking.contract.CustomerManager;
import dk.cphbusiness.banking.contract.MovementManager;
import net.sourceforge.plantuml.graph.Move;

import static dk.cphbusiness.banking.contract.AccountManager.*;
import static dk.cphbusiness.banking.contract.BankManager.*;
import static dk.cphbusiness.banking.contract.MovementManager.*;
import static dk.cphbusiness.banking.contract.CustomerManager.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DataAccessObject {
    //Account
    RealAccount createAccount(Account account);
    RealAccount getAccount(String accountNumber) throws Exception;
    List<RealAccount> getAccountsFromCustomer(String CPR);
    List<RealAccount> getAccountsFromBank(String CVR);
    RealAccount updateAccount(Account Account);
    void deleteAccount(String accountNumber);
    RealMovement transfer(RealAccount acc1, RealAccount acc2) throws Exception;

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

    void deleteCustomer();

    //Movement
    List<RealMovement> getMovements(String accountName);
    RealMovement createMovement(Movement movement);


}
