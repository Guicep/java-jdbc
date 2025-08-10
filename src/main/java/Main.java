import connections.ConnectionPostgreSQL;
import utils.CreateDatabase;

public class Main {
    public static void main(String[] args) {
        CreateDatabase createDatabase = new CreateDatabase();
        createDatabase.initialize();
        // App close
        ConnectionPostgreSQL conns = ConnectionPostgreSQL.getInstance();
        conns.closeConnections();
    }
}