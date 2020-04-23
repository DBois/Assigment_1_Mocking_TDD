package dk.cphbusiness.banking.backend.facade;

import dk.cphbusiness.banking.backend.datalayer.DAO;
import dk.cphbusiness.banking.backend.exceptions.RestException;
import dk.cphbusiness.banking.backend.models.RealAccount;
import dk.cphbusiness.banking.backend.models.RealClock;
import dk.cphbusiness.banking.backend.models.RealMovement;
import dk.cphbusiness.banking.backend.utility.AccountAssembler;
import dk.cphbusiness.banking.backend.utility.MovementAssembler;
import dk.cphbusiness.banking.contract.AccountManager;

import static dk.cphbusiness.banking.contract.MovementManager.*;

import java.util.List;

public class AccountFacade implements AccountManager {
    private DAO DAO;

    public AccountFacade() {
        this.DAO = new DAO();
    }

    public AccountDetail getAccount(String accountNumber) throws Exception {
        RealAccount account;
        try {
            account = DAO.getAccount(accountNumber);
            if (account == null) throw new RestException("Account not found", 404);
            return AccountAssembler.createAccountDetail(account);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<AccountSummary> getAccountsFromCustomer(String CPR) throws Exception {
        try {

            var accounts = DAO.getAccountsFromCustomer(CPR);
            if (accounts == null) throw new RestException("Accounts not found for the given id", 404);
            return AccountAssembler.createAccountSummaries(accounts);
        } catch (Exception e) {
            throw e;
        }

    }

    public MovementDetail transfer(long amount, String sourceNumber, String targetNumber) throws Exception {
        try {
            var acc1 = DAO.getAccount(sourceNumber);
            var acc2 = DAO.getAccount(targetNumber);
            var clock = new RealClock();
            acc1.transfer(amount, acc2, clock.getTime());
            RealMovement rm = DAO.transfer(acc1, acc2, clock.getTime());
            return MovementAssembler.createMovementDetail(rm);
        } catch (Exception e) {
            throw e;
        }

    }
}
