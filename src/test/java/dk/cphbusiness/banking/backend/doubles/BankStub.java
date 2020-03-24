package dk.cphbusiness.banking.backend.doubles;

import dk.cphbusiness.banking.backend.models.Account;
import dk.cphbusiness.banking.backend.models.RealAccount;
import dk.cphbusiness.banking.backend.models.Bank;
import dk.cphbusiness.banking.backend.models.Customer;

import java.util.List;

public class BankStub implements Bank {

    private Account account;
    private String cvr;

    public BankStub(RealAccount accountToStub){
        this.account = accountToStub;
    }
    public BankStub(){
    }

    @Override
    public Account getAccount(String number) {
        return this.account;
    }

    @Override
    public void registerAccount(Account Account) {

    }

    @Override
    public Customer getCustomer(String number) {
        return null;
    }

    @Override
    public void registerCustomer(Customer customer) {

    }

    @Override
    public List<Account> getAccounts(Customer customer) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getCvr() {
        return null;
    }

    public void setAccount(Account target) {
        this.account = target;
    }
}
