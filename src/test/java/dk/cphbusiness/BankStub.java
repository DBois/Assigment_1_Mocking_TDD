package dk.cphbusiness;

import dk.cphbusiness.banking.Account;
import dk.cphbusiness.banking.Bank;
import dk.cphbusiness.banking.Customer;

import java.util.List;

public class BankStub implements Bank {

    private Account account;


    BankStub(Account accountToStub){
        this.account = accountToStub;
    }
    BankStub(){
    }

    @Override
    public Account getAccount(String number) {
        return this.account;
    }

    @Override
    public void registerAccount(Account account) {

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
