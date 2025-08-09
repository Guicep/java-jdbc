package utils;

import connections.ConnectionPostgreSQL;

import java.sql.Connection;
import java.sql.Statement;

public class CreateDatabase {

    public void initialize() {
        ConnectionPostgreSQL connection = ConnectionPostgreSQL.getInstance();
        try {
            Connection conn = connection.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE DATABASE jdbc_db;");
            connection.addConnection(conn);
        } catch (ConnectionException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
