package dk.cphbusiness.banking.backend.utility;
import dk.cphbusiness.banking.backend.models.Customer;
import dk.cphbusiness.banking.contract.CustomerManager.*;


public class CustomerAssembler {
    public static CustomerSummary createCustomerSummary(Customer customer) {
        return new CustomerSummary(
                customer.getCpr(),
                customer.getName()
        );
    }

    public static CustomerDetail createCustomerDetail(Customer customer) {
        return new CustomerDetail(
                customer.getCpr(),
                customer.getName(),
                customer.getAccountNumbers()
        );
    }
}
