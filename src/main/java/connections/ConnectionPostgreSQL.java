package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import utils.ConnectionException;
import java.util.Stack;

public class ConnectionPostgreSQL {
    private final Stack<Connection> connectionStack;
    private static ConnectionPostgreSQL instance;

    private ConnectionPostgreSQL() throws ConnectionException {

        this.connectionStack = new Stack<>();

        final int MAX_POOL_SIZE = 10;

        Properties properties = new Properties();
        properties.put("user", "jdbc_user");
        properties.put("password", "jdbc_password");

        try {
            for (int i = 0; i < MAX_POOL_SIZE; i++) {
                this.connectionStack.push(
                        DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", properties)
                );
            }
        } catch (Exception e) {
            throw new ConnectionException("Fail to create connection to database");
        }
    }

    public static ConnectionPostgreSQL getInstance() {
        if (instance == null) {
            try {
                instance = new ConnectionPostgreSQL();
            } catch (ConnectionException e) {
                System.out.println(e.getMessage());
            }
        }
        return instance;
    }

    public Connection getConnection() throws ConnectionException {
        if (this.connectionStack.isEmpty()) {
            throw new ConnectionException("No connection available");
        }
        return this.connectionStack.pop();
    }

    public void addConnection(Connection connection) {
        this.connectionStack.push(connection);
    }

    public void closeConnections() {
        try {
            for (Connection conn : this.connectionStack) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Connection failed to close" + e.getMessage());
        }
        this.connectionStack.clear();
    }

    public int countConnections() {
        return this.connectionStack.size();
    }
}
