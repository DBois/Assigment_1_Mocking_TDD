package dk.cphbusiness.banking.backend.contract;

import dk.cphbusiness.banking.backend.doubles.BankDummy;
import dk.cphbusiness.banking.backend.models.Customer;
import dk.cphbusiness.banking.backend.models.RealBank;
import dk.cphbusiness.banking.backend.models.RealCustomer;
import dk.cphbusiness.banking.backend.utility.CustomerAssembler;
import dk.cphbusiness.banking.contract.CustomerManager;

import java.util.*;

public class CustomerManagerDummy implements CustomerManager {
    Map<String, Customer> customers;

    public CustomerManagerDummy() {
        RealBank rb = new RealBank("12345678", "Nordea");
        Customer cust1 = new RealCustomer("0101010001", "Kurt Wonnegut", rb);
        Customer cust2 = new RealCustomer("0101010002", "Michael Sebastiansen", new BankDummy());
        Customer cust3 = new RealCustomer("0101010003", "Adam Emilsen", new BankDummy());

        customers = new HashMap<>(){{
            put(cust1.getCpr(), cust1);
            put(cust2.getCpr(), cust2);
            put(cust3.getCpr(), cust3);

        }};
    }

    @Override
    public CustomerDetail getCustomer(String cpr) {
        return CustomerAssembler.createCustomerDetail(customers.get(cpr));
    }
}
