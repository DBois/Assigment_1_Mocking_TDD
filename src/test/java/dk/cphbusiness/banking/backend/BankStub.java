package dk.cphbusiness.banking.backend;

import dk.cphbusiness.banking.backend.models.Account;
import dk.cphbusiness.banking.backend.models.RealAccount;
import dk.cphbusiness.banking.backend.models.Bank;
import dk.cphbusiness.banking.backend.models.Customer;

import java.util.List;

public class BankStub implements Bank {

    private Account account;


    BankStub(RealAccount accountToStub){
        this.account = accountToStub;
    }
    BankStub(){
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

    public void setAccount(Account target) {
        this.account = target;
    }
}
