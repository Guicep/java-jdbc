package utils;

import connections.ConnectionPostgreSQL;

import java.sql.Connection;
import java.sql.Statement;

public class CreateDatabase {

    public void initialize() {
        ConnectionPostgreSQL connection = ConnectionPostgreSQL.getInstance();
        Connection conn = null;
        try {
            conn = connection.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE jdbc_db;");
        } catch (ConnectionException e) {
            System.out.println("Connection exception: "+ e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Closing connection exception: "+ e.getMessage());
            }
        }
    }
}
