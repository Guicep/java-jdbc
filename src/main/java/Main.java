import connections.ConnectionPostgreSQL;
import utils.CreateDatabase;

public class Main {
    public static void main(String[] args) {
        CreateDatabase createDatabase = new CreateDatabase();
        createDatabase.initialize();
        ConnectionPostgreSQL conn = ConnectionPostgreSQL.getInstance();
        conn.close();
        System.out.println("App finish");
    }
}