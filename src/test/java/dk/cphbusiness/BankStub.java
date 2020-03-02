package dk.cphbusiness;

import dk.cphbusiness.banking.Account;
import dk.cphbusiness.banking.Bank;

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
    public String getName() {
        return null;
    }

    public void setAccount(Account target) {
        this.account = target;
    }
}
