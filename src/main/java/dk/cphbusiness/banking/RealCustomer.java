package dk.cphbusiness.banking;

import java.util.ArrayList;
import java.util.List;

public class RealCustomer implements Customer {
    private String cpr, name;
    private Bank bank;
    private List<String> accountNumbers;
    public RealCustomer(String cpr, String name, Bank bank) {
        this.cpr = cpr;
        this.name = name;
        this.bank = bank;
        this.accountNumbers = new ArrayList<String>();
    }

    @Override
    public void transfer(long amount, Account account, Customer target) {

        var targetBank = target.getBank();
        var targetAccountNumber = target.getAccountNumbers().get(0);
        Account targAcc = targetBank.getAccount(targetAccountNumber);
        account.transfer(amount, targAcc);


    }

    @Override
    public String getCpr() {
        return this.cpr;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<String> getAccountNumbers() {
        return this.accountNumbers;
    }

    @Override
    public void addAccountNumber(String accountNumber) {
        accountNumbers.add(accountNumber);

    }

    public Bank getBank(){
        return this.bank;
    }

}
