package dk.cphbusiness.banking.backend.facade;

import dk.cphbusiness.banking.backend.datalayer.DAO;
import dk.cphbusiness.banking.backend.exceptions.RestException;
import dk.cphbusiness.banking.backend.models.RealCustomer;
import dk.cphbusiness.banking.backend.utility.CustomerAssembler;
import dk.cphbusiness.banking.contract.CustomerManager;

public class CustomerFacade implements CustomerManager {
    private DAO dao;

    public CustomerFacade() {
        dao = new DAO();
    }

    public CustomerDetail getCustomer(String cpr) throws Exception {
        RealCustomer customer;
        try {
            customer = dao.getCustomer(cpr);
            if(customer==null) throw new RestException("Customer does not exist", 404);
            return CustomerAssembler.createCustomerDetail(customer);
        } catch (Exception e) {
            throw e;
        }
    }

    public CustomerDetail updateCustomer(RealCustomer customer) throws Exception { ;
        try {
            customer = dao.updateCustomer(customer);
            if(customer==null) throw new Exception("Customer does not exist");
            return CustomerAssembler.createCustomerDetail(customer);
        } catch (Exception e) {
            throw e;
        }
    }
}
