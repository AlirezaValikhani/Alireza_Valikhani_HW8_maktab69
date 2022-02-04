package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton {
    private Connection connection;
    private static Singleton singletonConnection;
    private final String userName = "postgres";
    private final String password = "Alireza1376";
    private final String url = "jdbc:postgresql://localhost:5432/online_shop";

    public Singleton() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public static Singleton getInstance(){
        try {
            if (singletonConnection == null) {
                singletonConnection = new Singleton();
            } else if (singletonConnection.getConnection().isClosed()) {
                singletonConnection = new Singleton();
            }
            return singletonConnection;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return singletonConnection;
    }

}
