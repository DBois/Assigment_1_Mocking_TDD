package dk.cphbusiness.miscellaneous;

import dk.cphbusiness.banking.RealAccount;
import dk.cphbusiness.banking.Bank;
import dk.cphbusiness.banking.Movement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BankTool {

    public void readFromFile(Bank bank, String filename) throws IOException {
        List<String> lines = ReadFile(filename);
        parseStrings(bank, lines);
    }

    private void parseStrings(Bank bank, List<String> lines) {
        for (String line : lines) {
            if (line.startsWith("A:")) {
                parseAccounts(bank, line);
            } else if (line.startsWith("C:")) {
                parseCustomers(bank, line);
            } else if (line.startsWith("M:")) {
                parseMovements(bank, line);
            } else throw new RuntimeException();
        }
    }

    private void parseAccounts(Bank bank, String line) {
        var parts = line.split(":");
        if (parts.length != 3) throw new RuntimeException();
        var account = new RealAccount(bank, bank.getCustomer(parts[2]), parts[1]);
        bank.registerAccount(account);
    }

    private void parseCustomers(Bank bank, String line) {
        var customerParts = line.split(":");
        if (customerParts.length != 3) throw new RuntimeException();
        var customer = new BaseCustomer(customerParts[0], customerParts[1]);
        bank.registerCustomer(customer);
    }


    private void parseMovements(Bank bank, String line) {
        var movementParts = line.split(":");
        if (movementParts.length != 5) throw new RuntimeException();
        var sourceNumber = movementParts[0];
        var targetNumber = movementParts[1];
        long amount = Integer.parseInt(movementParts[2]);
        long timestamp = Integer.parseInt(movementParts[3]);
        Movement movement = new Movement(bank.getAccount(sourceNumber), bank.getAccount(targetNumber), amount, timestamp);
    }

    private List<String> ReadFile(String filename) throws IOException {
        return Files.readAllLines(Path.of(filename));
    }

}
