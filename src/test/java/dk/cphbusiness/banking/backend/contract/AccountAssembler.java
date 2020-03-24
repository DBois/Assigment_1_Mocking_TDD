package dk.cphbusiness.banking.backend.contract;
import dk.cphbusiness.banking.backend.models.Account;
import dk.cphbusiness.banking.backend.models.Bank;
import static dk.cphbusiness.banking.contract.AccountManager.*;
import static dk.cphbusiness.banking.backend.contract.BankAssembler.*;
import static dk.cphbusiness.banking.backend.contract.CustomerAssembler.*;
import static dk.cphbusiness.banking.backend.contract.MovementAssembler.*;

import java.util.*;

import static dk.cphbusiness.banking.contract.AccountManager.*;

public class AccountAssembler {

    public static AccountSummary createAccountSummary(Account account){
        return new AccountSummary(account.getNumber(), createBankSummary(account.getBank()), account.getBalance());
    }

    public static Map<String, AccountSummary> createAccountSummaries(Map<String, Account> accounts){
        var summaries = new HashMap<String, AccountSummary>();
        accounts.forEach((k,v) -> summaries.put(k, createAccountSummary(v)));
        return summaries;
    }

    public static AccountDetail createAccountDetail(Account account){
        return new AccountDetail(account.getNumber(),
                createBankSummary(account.getBank()),
                createCustomerSummary(account.getCustomer()),
                account.getBalance(),
                createMovementDetails(account.getMovements()));
    }
}
