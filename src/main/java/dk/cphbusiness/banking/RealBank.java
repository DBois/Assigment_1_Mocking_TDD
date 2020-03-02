package dk.cphbusiness.banking;

import java.util.*;

public class RealBank implements Bank {
    private Map<String, Account> accounts = new HashMap<>();
    private Map<String, Customer> customers = new HashMap<>();
    private String name;

    public RealBank(String name) {
        this.name = name;
    }

    @Override
    public Account getAccount(String number) {
        return accounts.get(number);
    }

    @Override
    public void registerAccount(Account account) {
        accounts.put(account.getNumber(), account);
    }

    @Override
    public Customer getCustomer(String cpr) {
        return customers.get(cpr);
    }

    @Override
    public void registerCustomer(Customer customer) {
        customers.put(customer.getCpr(), customer);
    }

    @Override
    public Collection<Account> getAccounts(Customer customer) {
        List<Account> accountsToReturn = new ArrayList<>();

        for (String accountName : customer.getAccountNames())
        {
            accountsToReturn.add(accounts.get(accountName));
        }
        return accountsToReturn;
    }

    @Override
    public String getName() {
        return name;
    }
}
