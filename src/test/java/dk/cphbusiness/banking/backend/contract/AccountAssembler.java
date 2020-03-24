package dk.cphbusiness.banking.backend.contract;
import dk.cphbusiness.banking.backend.models.Account;
import dk.cphbusiness.banking.backend.models.Bank;
import static dk.cphbusiness.banking.contract.AccountManager.*;
import static dk.cphbusiness.banking.backend.contract.BankAssembler.*;

import java.util.*;

import static dk.cphbusiness.banking.contract.AccountManager.*;

public class AccountAssembler {

    public static AccountSummary createAccountSummary(Account account){
        return new AccountSummary(account.getNumber(), createBankSummary(account.getBank()), account.getBalance());
    }

    public static Map<String, AccountSummary> createAccountSummaries(Map<String, Account> accounts){
        var summaries = new HashMap<String, AccountSummary>();
        for(String key : accounts.keySet()) {
            var account = accounts.get(key);
            summaries.put(key, createAccountSummary(account));
        }
        return summaries;
    }

//    public static AccountDetail createAccountDetail(Account account){
//        return new AccountDetail(account.getNumber(), createBankSummary(account.getBank(), a))
//    }
}
