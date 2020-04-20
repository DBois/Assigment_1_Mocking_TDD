package dk.cphbusiness.banking.backend.contract;

import dk.cphbusiness.banking.backend.models.*;
import dk.cphbusiness.banking.contract.AccountManager;
import static dk.cphbusiness.banking.contract.MovementManager.*;
import static dk.cphbusiness.banking.backend.utility.MovementAssembler.*;
import static dk.cphbusiness.banking.backend.utility.AccountAssembler.*;

import java.util.*;

public class AccountManagerDummy implements AccountManager {


    Map<String,Account> accounts;

    public AccountManagerDummy() {

        var bank = new RealBank("12345678", "Nordea");
        var customer = new RealCustomer("1008956666", "Adam", bank);
        
        var cpr1 = "0123456789";
        var cpr2 = "0012345678";
        accounts = new HashMap<>() {{
            put(cpr1, new RealAccount(bank, customer, cpr1));
            put(cpr1, new RealAccount(bank, customer, cpr1));
            put(cpr2, new RealAccount(bank, customer, cpr2));
        }};

    }

    @Override
    public AccountDetail getAccount(String s) {
        return createAccountDetail(accounts.get(s));
    }

    @Override
    public List<AccountSummary> getAccountsFromCustomer(String s) {
        var result = new ArrayList<AccountSummary>();
        accounts.forEach((k,v) -> {
            if(v.getCustomer().getCpr().equals(s)) result.add(createAccountSummary(v));
        });
        return result;
    }


    @Override
    public MovementDetail transfer(long amount, String sourceNumber, String targetNumber) {
        var source = accounts.get(sourceNumber);
        var target = accounts.get(targetNumber);
        var clock = new RealClock();
        source.transfer(amount, target, clock.getTime());
        var movements = createMovementDetails(source.getMovements());
        var detail = movements.get(movements.size() - 1);
        return detail;
    }
}
