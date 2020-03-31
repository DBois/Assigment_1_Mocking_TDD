package dk.cphbusiness.banking.backend.REST;

import dk.cphbusiness.banking.contract.AccountManager;
import dk.cphbusiness.banking.contract.BankManager;
import dk.cphbusiness.banking.contract.CustomerManager;
import dk.cphbusiness.banking.contract.MovementManager;

import java.util.List;
import java.util.Map;

public class AccountREST implements AccountManager{

    @Override
    public AccountDetail getAccount(String s) {
        return null;
    }

    @Override
    public Map<String, AccountSummary> getAccounts(String s) {
        return null;
    }

    @Override
    public MovementManager.MovementDetail transfer(long l, String s, String s1) {
        return null;
    }
}
