package dk.cphbusiness.banking.backend.contract;

import dk.cphbusiness.banking.backend.doubles.BankDummy;
import dk.cphbusiness.banking.backend.doubles.ClockDummy;
import dk.cphbusiness.banking.backend.doubles.CustomerDummy;
import dk.cphbusiness.banking.backend.models.Account;
import dk.cphbusiness.banking.backend.models.Bank;
import dk.cphbusiness.banking.backend.models.RealAccount;
import dk.cphbusiness.banking.backend.models.RealBank;
import dk.cphbusiness.banking.contract.AccountManager;
import static dk.cphbusiness.banking.contract.MovementManager.*;
import static dk.cphbusiness.banking.backend.contract.MovementAssembler.*;

import static dk.cphbusiness.banking.backend.contract.AccountAssembler.*;

import java.util.*;

public class AccountManagerDummy implements AccountManager {

    Map<String,Account> accounts;
    public AccountManagerDummy() {

        var bank = new BankDummy();
        var customer = new CustomerDummy();
        var clock = new ClockDummy();
        var cpr1 = "0123456789";
        var cpr2 = "001234567";
        accounts = new HashMap<>() {{
            put(cpr1, new RealAccount(bank, customer, cpr1, clock));
            put(cpr2, new RealAccount(bank, customer, cpr2, clock));
        }};

    }

    @Override
    public AccountDetail getAccount(String s) {
        return createAccountDetail(accounts.get(s));
    }

    @Override
    public Map<String, AccountSummary> getAccounts(String s) {
        return createAccountSummaries(accounts);
    }


    @Override
    public MovementDetail transfer(long amount, String sourceNumber, String targetNumber) {
        var source = accounts.get(sourceNumber);
        var target = accounts.get(targetNumber);
        source.transfer(amount, target);
        var movements = createMovementDetails(source.getMovements());
        var detail = movements.get(movements.size() - 1);
        return detail;
    }
}
