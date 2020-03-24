package dk.cphbusiness.banking.backend.models;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RealBank implements Bank {
    private Map<String, Account> accounts = new HashMap<>();
    private Map<String, Customer> customers = new HashMap<>();
    private String cvr, name;

    public RealBank(String cvr, String name) {
        this.cvr = cvr;
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
    public Customer getCustomer(String number) {
        return customers.get(number);
    }

    @Override
    public void registerCustomer(Customer customer) {
        customers.put(customer.getCpr(), customer);
    }

    @Override
    public List<Account> getAccounts(Customer customer) {
        List<Account> accountsToReturn = new ArrayList<>();
        for (String accNumber : customer.getAccountNumbers()
        ) {
            accountsToReturn.add(accounts.get(accNumber));
        }
        return accountsToReturn;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCvr() {
        return this.cvr;
    }
}
