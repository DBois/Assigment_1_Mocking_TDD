package dk.cphbusiness.banking.backend.datalayer;

import dk.cphbusiness.banking.backend.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dk.cphbusiness.banking.backend.settings.Settings.*;

public class DAO implements DataAccessObject {
    private String databaseName;

    public DAO() {
        this.databaseName = DB_NAME;
    }

    @Override
    public RealAccount createAccount(Account account) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RealAccount getAccount(String accountNumber) throws Exception {
        Connection conn = DBConnector.connection(databaseName);

        try {
            return getAccountHelper(accountNumber, conn);
        } catch (Exception e) {
            conn.rollback();
            if (env == Environment.DEVELOPMENT) throw e;
            throw new Exception("Something went wrong getting account from database");
        } finally {
            conn.close();
        }
    }

    @Override
    public List<RealAccount> getAccountsFromCustomer(String CPR) throws Exception {
        var accounts = new ArrayList<RealAccount>();
        Connection conn = DBConnector.connection(databaseName);

        try {
            conn.setAutoCommit(false);
            String SQL = "SELECT * FROM account WHERE customer_cpr=?";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.setString(1, CPR);

            ResultSet rs = ps.executeQuery();

            RealAccount account = null;
            while (rs.next()) {
                long balance = rs.getLong("balance");
                String cpr = rs.getString("customer_cpr");
                String bankCvr = rs.getString("bank_cvr");
                String accountNumber = rs.getString("number");
                String SQL2 = "SELECT * FROM \"bank\" WHERE cvr=?";

                PreparedStatement ps2 = conn.prepareStatement(SQL2);
                ps2.setString(1, bankCvr);

                ResultSet rs2 = ps2.executeQuery();
                RealBank bank = null;
                if (rs2.next()) {
                    String bankName = rs2.getString("name");
                    bank = new RealBank(bankCvr, bankName);
                }

                String SQL3 = "SELECT * FROM \"customer\" WHERE cpr=?";
                PreparedStatement ps3 = conn.prepareStatement(SQL3);
                ps3.setString(1, cpr);
                ResultSet rs3 = ps3.executeQuery();

                RealCustomer customer = null;
                if (rs3.next()) {
                    String customerName = rs3.getString("name");
                    customer = new RealCustomer(cpr, customerName);
                }
                account = new RealAccount(bank, customer, accountNumber, balance);
                accounts.add(account);
            }
            return accounts;
        } catch (Exception e) {
            conn.rollback();
            if (env == Environment.DEVELOPMENT) throw e;
            throw new Exception("Something went wrong getting account from database");
        } finally {
            conn.close();
        }
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
    public RealMovement transfer(RealAccount source, RealAccount target, long timestamp) throws Exception {
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
            ps2.setLong(1, target.getBalance());
            ps2.setString(2, target.getNumber());

            //Create Movement
            var movement = source.getMovements().get(source.getMovements().size() - 1); //gets last movement to insert into DB
            var SQL2 = "INSERT INTO movement (time, amount, account_source, account_target) VALUES (?, ?, ?, ?)";
            PreparedStatement ps3 = conn.prepareStatement(SQL2, Statement.RETURN_GENERATED_KEYS);
            ps3.setLong(1, timestamp);
            ps3.setLong(2, movement.getAmount());
            ps3.setString(3, movement.getSource());
            ps3.setString(4, movement.getTarget());

            ps1.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
            conn.commit();

            var id = getIdentifier(ps3.getGeneratedKeys());

            ps1.close();
            ps2.close();
            ps3.close();

            return new RealMovement(id, movement.getTime(), movement.getAmount(), movement.getSource(), movement.getTarget());
        } catch (Exception e) {

            conn.rollback();
            if (env == Environment.DEVELOPMENT) throw e;
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
    public RealCustomer getCustomer(String CPR) throws Exception {
        Connection conn = DBConnector.connection(databaseName);
        try {
            conn.setAutoCommit(false);
            var SQL = "SELECT * FROM CUSTOMER WHERE cpr = ?";

            var ps = conn.prepareStatement(SQL);
            ps.setString(1, CPR);
            var rs = ps.executeQuery();
            RealCustomer customer = null;
            if (rs.next()) {
                var cpr = rs.getString("cpr");
                var name = rs.getString("name");
                customer = new RealCustomer(cpr, name);

                //Query and add account numbers to RealCustomer
                var SQL1 = "SELECT number as \"number\" FROM account WHERE customer_cpr=?";
                var ps1 = conn.prepareStatement(SQL1);
                ps1.setString(1, cpr);
                var rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    var number = rs1.getString("number");
                    customer.addAccountNumber(number);
                }
            }
            ps.close();
            conn.commit();
            return customer;
        } catch (Exception e) {
            conn.rollback();
            if (env == Environment.DEVELOPMENT) throw e;
            throw new Exception("Transfer went wrong");
        } finally {
            conn.close();
        }
    }

    @Override
    public List<RealCustomer> getCustomers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public RealCustomer updateCustomer(RealCustomer customer) throws Exception {
        var conn = DBConnector.connection(databaseName);
        try {
            conn.setAutoCommit(false);
            //Update
            String SQL = "UPDATE customer SET name = ? WHERE cpr=?";

            //Update customer
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getCpr());

            var hasUpdated = ps.executeUpdate();
            if(hasUpdated == 0)
                return null;
            ps.close();
            conn.commit();
            return customer;
        } catch (Exception e) {
            conn.rollback();
            if (env == Environment.DEVELOPMENT) throw e;
            throw new Exception("Update on customer went wrong");
        } finally {
            conn.close();
        }
    }


    @Override
    public void deleteCustomer(String cpr) throws Exception {
        var conn = DBConnector.connection(databaseName);
        try {
            conn.setAutoCommit(false);
            //Update accounts with correct pricing
            String SQL = "DELETE FROM customer WHERE cpr=?";

            //Update customer
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, cpr);

            var hasDeleted = ps.executeUpdate();
            ps.close();

            if (hasDeleted == 0)
                throw new Exception("Deletion of customer went wrong");
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            if (env == Environment.DEVELOPMENT) throw e;
            throw new Exception("Deletion of customer went wrong");
        } finally {
            conn.close();
        }
    }

    @Override
    public List<RealMovement> getMovements(String accountNumber) throws Exception {
        List<RealMovement> movements = new ArrayList<>();
        Connection conn = DBConnector.connection(databaseName);

        try {
            conn.setAutoCommit(false);
            String SQL = "SELECT * FROM movement WHERE account_source=? OR account_target=?";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, accountNumber);
            ps.setString(2, accountNumber);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                //Get fields for movement
                var id = rs.getLong("id");
                var accountSource = rs.getString("account_source");
                var accountTarget = rs.getString("account_target");
                var time = rs.getLong("time");
                long amount = rs.getLong("amount");

                RealMovement movement = new RealMovement(id, time, amount, accountSource, accountTarget);
                movements.add(movement);
            }
            return movements;
        } catch (Exception e) {
            conn.rollback();
            if (env == Environment.DEVELOPMENT) throw e;
            throw new Exception("Something went wrong getting account from database");
        } finally {
            conn.close();
        }


    }

    private long getIdentifier(ResultSet rs) throws SQLException {
        var id = 0L;
        if (rs.next()) id = rs.getLong(1);
        return id;
    }

    private RealAccount getAccountHelper(String accountNumber, Connection conn) throws Exception {
        RealAccount account = null;
        try {
            conn.setAutoCommit(false);
            String SQL = "SELECT * FROM account WHERE number=?";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.setString(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                long balance = rs.getLong("balance");
                String cpr = rs.getString("customer_cpr");
                String bankCvr = rs.getString("bank_cvr");
                String SQL2 = "SELECT * FROM \"bank\" WHERE cvr=?";

                PreparedStatement ps2 = conn.prepareStatement(SQL2);
                ps2.setString(1, bankCvr);

                ResultSet rs2 = ps2.executeQuery();
                RealBank bank = null;
                if (rs2.next()) {
                    String bankName = rs2.getString("name");
                    bank = new RealBank(bankCvr, bankName);
                }

                String SQL3 = "SELECT * FROM \"customer\" WHERE cpr=?";
                PreparedStatement ps3 = conn.prepareStatement(SQL3);
                ps3.setString(1, cpr);
                ResultSet rs3 = ps3.executeQuery();

                RealCustomer customer = null;
                if (rs3.next()) {
                    String customerName = rs3.getString("name");
                    customer = new RealCustomer(cpr, customerName);
                }
                account = new RealAccount(bank, customer, accountNumber, balance);

            }
            return account;
        } catch (Exception e) {
            conn.rollback();
            if (env == Environment.DEVELOPMENT) throw e;
            throw new Exception("Something went wrong getting account from database");
        } finally {
        }
    }

}
