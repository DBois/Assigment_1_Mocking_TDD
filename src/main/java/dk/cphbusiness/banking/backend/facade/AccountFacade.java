package dk.cphbusiness.banking.backend.facade;

import dk.cphbusiness.banking.backend.datalayer.DAO;
import dk.cphbusiness.banking.backend.models.Account;
import dk.cphbusiness.banking.backend.models.RealAccount;
import dk.cphbusiness.banking.backend.models.RealClock;
import dk.cphbusiness.banking.backend.models.RealMovement;
import dk.cphbusiness.banking.backend.utility.AccountAssembler;
import dk.cphbusiness.banking.backend.utility.MovementAssembler;
import dk.cphbusiness.banking.contract.AccountManager;

import static dk.cphbusiness.banking.contract.AccountManager.*;
import static dk.cphbusiness.banking.contract.MovementManager.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static dk.cphbusiness.banking.backend.settings.Settings.*;

public class AccountFacade implements AccountManager {
    private DAO DAO;

    public AccountFacade() {
        this.DAO = new DAO();
    }

    public AccountDetail getAccount(String accountNumber) throws Exception {
        RealAccount account;
        try {
            account = DAO.getAccount(accountNumber);
            if (account == null) throw new Exception("Account does not exist");
            return AccountAssembler.createAccountDetail(account);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<AccountSummary> getAccountsFromCustomer(String CPR) throws Exception
    {
        var accounts = DAO.getAccountsFromCustomer(CPR);
        return AccountAssembler.createAccountSummaries(accounts);
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
