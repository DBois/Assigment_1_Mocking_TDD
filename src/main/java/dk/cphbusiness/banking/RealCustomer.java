package dk.cphbusiness.banking;

import java.util.List;

public class RealCustomer implements Customer {
    private String cpr;
    private String name;

    public RealCustomer(String cpr, String name) {
        this.cpr = cpr;
        this.name = name;
    }

    @Override
    public void transfer(long amount, Account account, Customer target) {

    }

    @Override
    public String getCpr() {
        return cpr;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getAccountNumbers() {
        return ;
    }
}
