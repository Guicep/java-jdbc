package connections;

import java.sql.Connection;
import utils.ConnectionException;
import org.postgresql.ds.PGSimpleDataSource;

public class ConnectionPostgreSQL {
    private static ConnectionPostgreSQL instance;
    private PGSimpleDataSource dataSource;

    private ConnectionPostgreSQL() throws ConnectionException {

        final int MAX_POOL_SIZE = 10;
        this.dataSource = new PGSimpleDataSource();
        this.dataSource.setServerNames(new String[]{"localhost"});
        this.dataSource.setPortNumbers(new int[]{5432});
        this.dataSource.setDatabaseName("postgres");
        this.dataSource.setUser("jdbc_user");
        this.dataSource.setPassword("jdbc_password");
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
        Connection conn = null;
        try {
            conn = this.dataSource.getConnection();
        } catch (Exception e) {
            throw new ConnectionException("Fail to connect: " + e.getMessage());
        }
        return conn;
    }
}
