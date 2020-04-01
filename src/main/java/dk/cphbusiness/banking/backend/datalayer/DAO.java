package dk.cphbusiness.banking.backend.datalayer;

import dk.cphbusiness.banking.backend.models.*;

import static dk.cphbusiness.banking.contract.AccountManager.*;
import static dk.cphbusiness.banking.contract.BankManager.*;
import static dk.cphbusiness.banking.contract.CustomerManager.*;
import static dk.cphbusiness.banking.contract.MovementManager.*;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class DAO implements DataAccessObject {
    private String databaseName;

    public DAO(String databaseName) {
        this.databaseName = databaseName;
    }

//    public void structureMethod() throws IOException, SQLException {
//        Connection conn = DBConnector.connection(databaseName);
//
//        try {
//            conn.setAutoCommit(false);
//            String SQL = "INSERT INTO customer (cpr, name) VALUES (?,?)";
//            PreparedStatement ps = conn.prepareStatement(SQL);
//            ps.setString(1, "1049560293");
//            ps.setString(2, "Adam");
//            ResultSet rs = ps.executeQuery();
//            conn.commit();
//        } catch (Exception e) {
//            conn.rollback();
//        } finally {
//            conn.close();
//        }
//    }

    @Override
    public RealAccount createAccount(Account account) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RealAccount getAccount(String accountNumber) throws Exception {
        RealAccount account = null;
        Connection conn = DBConnector.connection(databaseName);

        try {
            conn.setAutoCommit(false);
            String SQL = "SELECT * FROM account WHERE number=?";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.setString(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {

                long balance = rs.getLong("balance");
                String cpr = rs.getString("customer_cpr");
                String bankCvr = rs.getString("bank_cvr");
                String SQL2 = "SELECT * FROM bank WHERE cvr=?";

                PreparedStatement ps2 = conn.prepareStatement(SQL2);

                ps2.setString(1, bankCvr);

                ResultSet rs2 = ps.executeQuery();

                RealBank bank = null;
                if (rs2.next())
                {
                    String bankName = rs2.getString("name");
                    bank = new RealBank(bankCvr, bankName);
                }

                String SQL3 = "SELECT * FROM customer WHERE cpr=?";
                PreparedStatement ps3 = conn.prepareStatement(SQL3);

                ps3.setString(1, cpr);

                ResultSet rs3 = ps.executeQuery();

                RealCustomer customer = null;
                if (rs3.next())
                {
                    String customerName = rs3.getString("name");
                    customer = new RealCustomer(cpr, customerName, bank);
                }
                RealClock clock = new RealClock();
                account = new RealAccount(bank, customer, accountNumber, clock);
            }

        } catch (Exception ex)
        {
            conn.rollback();
            throw new Exception("Something went wrong getting account from database");
        }
        return account;
    }

    @Override
    public List<RealAccount> getAccountsFromCustomer(String CPR) {
        return null;
    }

    @Override
    public List<RealAccount> getAccountsFromBank(String CVR) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RealAccount updateAccount(Account Account) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RealMovement transfer(RealAccount source, RealAccount target) throws Exception {
        Connection conn = DBConnector.connection(databaseName);

        try {
            conn.setAutoCommit(false);

            //Update accounts with correct pricing
            String SQL = "UPDATE account SET balance = ? WHERE number=?";

            //Update source
            PreparedStatement ps1 = conn.prepareStatement(SQL);
            ps1.setLong(1, source.getBalance());
            ps1.setString(2, source.getNumber());

            //Update target
            PreparedStatement ps2 = conn.prepareStatement(SQL);
            ps2.setLong(1, source.getBalance());
            ps2.setString(2, source.getNumber());

            //Create Movement
            var movement = source.getMovements().get(source.getMovements().size()-1); //gets last movement to insert into DB
            var SQL2 = "INSERT INTO movement (amount, account_source, account_target) VALUES (?, ?, ?)";
            PreparedStatement ps3 = conn.prepareStatement(SQL2, Statement.RETURN_GENERATED_KEYS);
            ps3.setLong(1, movement.getAmount());
            ps3.setString(2, movement.getSource().getNumber());
            ps3.setString(3, movement.getTarget().getNumber());

            ps1.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
            ps1.close();
            ps2.close();
            ps3.close();
            conn.commit();

            var rs = ps3.getGeneratedKeys();
            int id = 0;
            if(rs.next()) id = rs.getInt(1);

            var SQL3 = "SELECT * from movement WHERE id=?";

            //Update source
            PreparedStatement ps4 = conn.prepareStatement(SQL3);
            ps4.setLong(1, id);
            ps4.executeQuery();
            rs = ps4.getResultSet();


            var time = 0L;
            if (rs.next())
            {
                time = rs.getTimestamp("time").getTime();
            }

            var movement2 = new RealMovement(id, time, movement.getAmount(), movement.getSource(), movement.getTarget());


            return movement2;
        } catch (Exception e) {
            conn.rollback();
            throw new Exception("Transfer went wrong");
        } finally {
            conn.close();
        }

    }

    @Override
    public void deleteAccount(String accountNumber) {
        throw new UnsupportedOperationException();
    }


    @Override
    public RealBank createBank(Bank bank) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RealBank getBank(String CVR) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<RealBank> getBanks() {
        throw new UnsupportedOperationException();
    }

    @Override
    public RealBank updateBank(Bank bank) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteBank(String CVR) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RealCustomer createCustomer(Customer customer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RealCustomer getCustomer(String CPR) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<RealCustomer> getCustomers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public RealCustomer updateCustomer() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteCustomer() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<RealMovement> getMovements(String accountName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RealMovement createMovement(Movement movement) {
        return null;
    }


}
