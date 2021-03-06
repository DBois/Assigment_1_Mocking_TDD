package dk.cphbusiness.banking.backend.doubles;

import dk.cphbusiness.banking.backend.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerStub implements Customer {

    String cpr, name;
    List<String> accountNumbers;

    public CustomerStub(String cpr, String name) {
        this.cpr = cpr;
        this.name = name;
        accountNumbers = new ArrayList<>();
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
        return accountNumbers;
    }

    @Override
    public void addAccountNumber(String accountNumber) {

    }

}
