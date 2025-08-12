package connections;

import java.sql.Connection;
import java.util.Properties;
import java.io.PrintWriter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import utils.ConnectionException;

public class ConnectionPostgreSQL {
    private static ConnectionPostgreSQL instance;
    private final HikariDataSource dataSource;

    private ConnectionPostgreSQL() throws ConnectionException {

        final int MAX_POOL_SIZE = 5;
        Properties props = new Properties();
        props.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        props.setProperty("dataSource.user", "jdbc_user");
        props.setProperty("dataSource.password", "jdbc_password");
        props.setProperty("dataSource.databaseName", "postgres");
        props.setProperty("poolName", "jdbc_pool");
        props.setProperty("maximumPoolSize", Integer.toString(MAX_POOL_SIZE));
        props.put("dataSource.logWriter", new PrintWriter(System.out));
        HikariConfig config = new HikariConfig(props);
        this.dataSource = new HikariDataSource(config);
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

    public void close() {
        this.dataSource.close();
    }
}
