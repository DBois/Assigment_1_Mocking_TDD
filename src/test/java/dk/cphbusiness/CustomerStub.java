package dk.cphbusiness;

import dk.cphbusiness.banking.Account;
import dk.cphbusiness.banking.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerStub implements Customer {
    private String cpr, name;
    private List<String> accountNames;

    public CustomerStub(String cpr, String name) {
        this.cpr = cpr;
        this.name = name;
        accountNames = new ArrayList<>();
    }

    @Override
    public void transfer(long amount, Account account, Customer target) {

    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getName() {
        return name;
    }

    @Override
    public List<String> getAccountNames() {
        return accountNames;
    }

    public void addAccountName(String accountName){
        accountNames.add(accountName);

    };

    public void setName(String name) {
        this.name = name;
    }
}
