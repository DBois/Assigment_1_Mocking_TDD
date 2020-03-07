package dk.cphbusiness;

import dk.cphbusiness.banking.RealAccount;
import dk.cphbusiness.banking.Bank;
import dk.cphbusiness.banking.Customer;

import java.util.List;

public class BankStub implements Bank {

    private RealAccount account;


    BankStub(RealAccount accountToStub){
        this.account = accountToStub;
    }
    BankStub(){
    }

    @Override
    public RealAccount getAccount(String number) {
        return this.account;
    }

    @Override
    public void registerAccount(RealAccount account) {

    }

    @Override
    public Customer getCustomer(String number) {
        return null;
    }

    @Override
    public void registerCustomer(Customer customer) {

    }

    @Override
    public List<RealAccount> getAccounts(Customer customer) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    public void setAccount(RealAccount target) {
        this.account = target;
    }
}
