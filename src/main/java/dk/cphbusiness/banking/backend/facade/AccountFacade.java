package dk.cphbusiness.banking.backend.facade;

import dk.cphbusiness.banking.backend.datalayer.DAO;
import dk.cphbusiness.banking.backend.models.RealClock;
import dk.cphbusiness.banking.backend.models.RealMovement;
import dk.cphbusiness.banking.backend.utility.AccountAssembler;
import dk.cphbusiness.banking.backend.utility.MovementAssembler;

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

    public AccountDetail getAccount(String accountName) throws Exception {
        var account = DAO.getAccount(accountName);
        return AccountAssembler.createAccountDetail(account);
    }

    public Map<String, AccountSummary> getAccounts(String CPR) throws Exception {
        DAO.getAccountsFromCustomer(CPR);
        return null;
    }

    public MovementDetail transfer(long amount, String sourceNumber, String targetNumber) throws Exception {
        var acc1 = DAO.getAccount(sourceNumber);
        var acc2 = DAO.getAccount(targetNumber);
        var clock = new RealClock();
        acc1.transfer(amount, acc2, clock.getTime());

        RealMovement rm = DAO.transfer(acc1, acc2, clock.getTime());

        return MovementAssembler.createMovementDetail(rm);
    }
}
