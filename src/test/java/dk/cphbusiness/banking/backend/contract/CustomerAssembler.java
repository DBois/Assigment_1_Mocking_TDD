package dk.cphbusiness.banking.backend.contract;
import dk.cphbusiness.banking.backend.models.Customer;
import dk.cphbusiness.banking.contract.CustomerManager.*;

import static dk.cphbusiness.banking.backend.contract.BankAssembler.*;

public class CustomerAssembler {
    public static CustomerSummary createCustomerSummary(Customer customer) {
        return new CustomerSummary(
                customer.getCpr(),
                customer.getName(),
                createBankSummary(customer.getBank())
        );
    }

    public static CustomerDetail createCustomerDetail(Customer customer) {
        return new CustomerDetail(
                customer.getCpr(),
                customer.getName(),
                createBankSummary(customer.getBank()),
                customer.getAccountNumbers()
        );
    }
}
