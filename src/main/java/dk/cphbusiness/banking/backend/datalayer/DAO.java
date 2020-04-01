package dk.cphbusiness.banking.backend.datalayer;

import dk.cphbusiness.banking.backend.models.Account;
import dk.cphbusiness.banking.backend.models.Bank;
import dk.cphbusiness.banking.backend.models.Customer;
import static dk.cphbusiness.banking.contract.AccountManager.*;
import static dk.cphbusiness.banking.contract.BankManager.*;
import static dk.cphbusiness.banking.contract.CustomerManager.*;
import static dk.cphbusiness.banking.contract.MovementManager.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DAO implements DataAccessObject {

    private static String databaseName = "test_bank";


    public static void CreatUser() {
        try {
            Connection conn = DBConnector.connection(databaseName);
            String SQL = "INSERT INTO customer (cpr, name) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "1049560293");
            ps.setString(2, "Adam");
            ResultSet rs = ps.executeQuery();
        } catch (Exception e) {

        }
    }

    @Override
    public AccountDetail createAccount(Account account) {
        return null;
    }

    @Override
    public AccountDetail getAccount(String accountNumber) {
        return null;
    }

    @Override
    public List<AccountSummary> getAccountsFromCustomer(String CPR) {
        return null;
    }

    @Override
    public List<AccountSummary> getAccountsFromBank(String CVR) {
        return null;
    }

    @Override
    public AccountDetail updateAccount(Account Account) {
        return null;
    }

    @Override
    public void deleteAccount(String accountNumber) {

    }

    @Override
    public BankDetail createBank(Bank bank) {
        return null;
    }

    @Override
    public BankDetail getBank(String CVR) {
        return null;
    }

    @Override
    public List<BankSummary> getBanks() {
        return null;
    }

    @Override
    public BankDetail updateBank(Bank bank) {
        return null;
    }

    @Override
    public void deleteBank(String CVR) {

    }

    @Override
    public CustomerDetail createCustomer(Customer customer) {
        return null;
    }

    @Override
    public CustomerDetail getCustomer(String CPR) {
        return null;
    }

    @Override
    public List<CustomerSummary> getCustomers() {
        return null;
    }

    @Override
    public CustomerDetail updateCustomer() {
        return null;
    }

    @Override
    public void deleteCustomer() {

    }

    @Override
    public List<MovementDetail> getMovements(String accountName) {
        return null;
    }
}
