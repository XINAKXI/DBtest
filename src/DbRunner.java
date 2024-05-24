import java.sql.*;

public class DbRunner {

    public static final String DB_URL = "jdbc:postgresql://localhost:5432/habrdb";
    public static final String USER = "habrpguser";
    public static final String PASS = "pgpwd4habr";

    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
    public static int changes;


    public static void main(String[] args) {

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            if(statement != null){
               CommandsFromUser.Commands();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
           /* try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println(":)");
            }*/
        }
    }
}

