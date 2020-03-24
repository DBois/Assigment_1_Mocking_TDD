package dk.cphbusiness.banking.backend.contract;

import dk.cphbusiness.banking.backend.BankDummy;
import dk.cphbusiness.banking.backend.models.Customer;
import dk.cphbusiness.banking.backend.models.RealCustomer;
import dk.cphbusiness.banking.contract.CustomerManager;

import java.util.*;

public class CustomerManagerDummy implements CustomerManager {
    Map<String, Customer> customers;

    public CustomerManagerDummy() {
        Customer cust1 = new RealCustomer("0101010001", "Kurt Wonnegut", new BankDummy());
        Customer cust2 = new RealCustomer("0101010002", "Michael Sebastiansen", new BankDummy());
        Customer cust3 = new RealCustomer("0101010003", "Adam Emilsen", new BankDummy());

        customers = new HashMap<>(){{
            customers.put(cust1.getCpr(), cust1);
            customers.put(cust2.getCpr(), cust2);
            customers.put(cust3.getCpr(), cust3);

        }};
    }

    @Override
    public CustomerDetail getCustomer(String cpr) {
        return CustomerAssembler.createCustomerDetail(customers.get(cpr));
    }
}
