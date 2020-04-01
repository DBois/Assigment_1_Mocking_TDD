package dk.cphbusiness.banking.backend.facade;

import dk.cphbusiness.banking.backend.datalayer.DAO;

import static dk.cphbusiness.banking.contract.AccountManager.*;
import static dk.cphbusiness.banking.contract.MovementManager.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class AccountFacade  {
    private DAO DAO;

    public AccountFacade(DAO DAO) {
        this.DAO = new DAO("test_bank");
    }

    public AccountDetail getAccount(String accountName) {
        DAO.getAccount(accountName);
        return null;
    }

    public Map<String, AccountSummary> getAccounts(String CPR) {
        DAO.getAccountsFromCustomer(CPR);
        return null;
    }

    public MovementDetail transfer(long amount, String sourceNumber, String targetNumber) throws IOException, SQLException {
        var acc1 = DAO.getAccount(sourceNumber);
        var acc2 = DAO.getAccount(targetNumber);

        acc1.transfer(amount, acc2);

        DAO.transfer(acc1, acc2);
        return null;
    }
}
