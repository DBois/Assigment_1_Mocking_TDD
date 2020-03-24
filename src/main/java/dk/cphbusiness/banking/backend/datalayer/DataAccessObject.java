package dk.cphbusiness.banking.backend.datalayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataAccessObject {

    public static void CreatUser() {
        try {
            Connection conn = DBConnector.connection();
            String SQL = "INSERT INTO customer (cpr, name) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "1049560293");
            ps.setString(2, "Adam");
            ResultSet rs = ps.executeQuery();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        CreatUser();
    }
}
