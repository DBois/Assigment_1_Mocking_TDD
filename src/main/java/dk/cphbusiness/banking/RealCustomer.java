package dk.cphbusiness.banking;

import java.util.ArrayList;
import java.util.List;

public class RealCustomer implements Customer {
    private Bank bank;
    private String cpr;
    private String name;
    private List<String> accountNumbers;

    public RealCustomer(String cpr, String name, Bank bank) {
        accountNumbers = new ArrayList<>();
        this.cpr = cpr;
        this.name = name;
        this.bank = bank;
    }

    @Override
    public void transfer(long amount, Account account, Customer target) {
        account.updateBalance(-amount);

        //Get customer targets first account
        var targetAccount = target.getBank().getAccount(target.getAccountNumbers().get(0));
        targetAccount.updateBalance(amount);
    }

    @Override
    public String getCpr() {
        return cpr;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getAccountNumbers() {
        return accountNumbers;
    }

    @Override
    public void addAccountNumber(String accountNumber){
        accountNumbers.add(accountNumber);
    }

    @Override
    public Bank getBank() {
        return bank;
    }
}
