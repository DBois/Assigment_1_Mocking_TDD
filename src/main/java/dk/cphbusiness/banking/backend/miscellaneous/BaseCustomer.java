package dk.cphbusiness.banking.backend.miscellaneous;

import dk.cphbusiness.banking.backend.models.Customer;

import java.util.List;

public class BaseCustomer implements Customer {
    public BaseCustomer(String number, String name) { }




    @Override
    public String getCpr() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<String> getAccountNumbers() {
        return null;
    }

    @Override
    public void addAccountNumber(String accountNumber) {

    }

}