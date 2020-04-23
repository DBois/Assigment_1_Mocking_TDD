package dk.cphbusiness.banking.backend.models;

import java.util.ArrayList;
import java.util.List;

public class RealCustomer implements Customer {
    private String cpr, name;
    private List<String> accountNumbers;

    public RealCustomer(){
    }

    public RealCustomer(String cpr, String name) {
        this.cpr = cpr;
        this.name = name;
        this.accountNumbers = new ArrayList<String>();
    }

    @Override
    public String getCpr() {
        return this.cpr;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<String> getAccountNumbers() {
        return this.accountNumbers;
    }

    @Override
    public void addAccountNumber(String accountNumber) {
        accountNumbers.add(accountNumber);

    }

}
