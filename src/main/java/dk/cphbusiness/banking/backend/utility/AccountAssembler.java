package dk.cphbusiness.banking.backend.utility;
import dk.cphbusiness.banking.backend.models.Account;

import static dk.cphbusiness.banking.backend.utility.BankAssembler.createBankSummary;
import static dk.cphbusiness.banking.contract.AccountManager.*;
import static dk.cphbusiness.banking.backend.utility.CustomerAssembler.*;
import static dk.cphbusiness.banking.backend.utility.MovementAssembler.*;

import java.util.*;

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
