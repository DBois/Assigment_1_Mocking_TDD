package dk.cphbusiness.banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RealBank implements Bank {
    private Map<String, RealAccount> accounts = new HashMap<>();
    private Map<String, Customer> customers = new HashMap<>();
    private String name;

    public RealBank(String name) {
        this.name = name;
    }

    @Override
    public RealAccount getAccount(String number) {
        return accounts.get(number);
    }

    @Override
    public void registerAccount(RealAccount account) {
        accounts.put(account.getNumber(), account);
    }

    @Override
    public Customer getCustomer(String number) {
        return customers.get(number);
    }

    @Override
    public void registerCustomer(Customer customer) {
        customers.put(customer.getCpr(), customer);
    }

    @Override
    public List<RealAccount> getAccounts(Customer customer) {
        List<RealAccount> accountsToReturn = new ArrayList<>();
        for (String accNumber : customer.getAccountNumbers()
        ) {
            accountsToReturn.add(accounts.get(accNumber));
        }
        return accountsToReturn;
    }

    @Override
    public String getName() {
        return null;
    }
}
