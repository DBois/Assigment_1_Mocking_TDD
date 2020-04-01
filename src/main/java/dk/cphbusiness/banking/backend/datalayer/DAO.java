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
    public RealAccount getAccount(String accountNumber) {
        return null;
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
    public MovementDetail transfer(RealAccount source, RealAccount target) throws Exception {
        Connection conn = DBConnector.connection(databaseName);

        try {
            conn.setAutoCommit(false);

            //Update accounts with correct pricing
            String SQL = "UPDATE INTO account (balance) VALUES (?) WHERE number=?";

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
            SQL = "INSERT INTO movement (amount, account_source, account_target) VALUES (?, ?, ?)";
            PreparedStatement ps3 = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
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

            SQL = "SELECT * from movement WHERE id=?";

            //Update source
            PreparedStatement ps4 = conn.prepareStatement(SQL);
            ps4.setLong(1, id);
            ps4.executeQuery();
            rs = ps4.getResultSet();

            if (rs.next())
            {
                String accountSource = rs.getString("account_source");
                String accountTarget = rs.getString("account_target");

            }


//            return
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
