package dk.cphbusiness.banking;

public class Account {
    private Bank bank;
    public Account(Bank bank, Customer customer, String number){
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }
}
