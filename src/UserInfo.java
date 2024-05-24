import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserInfo {
    public static void Info(User user, BufferedReader reader) {
        String queryINFO = "SELECT * FROM users WHERE login = (?)";
        System.out.println("Available commands : 'INFO' or 'I' :)");
        try {
           String comm = reader.readLine();
            if (comm.equals("INFO") || comm.equals("I")){
                PreparedStatement INFO = DbRunner.connection.prepareStatement(queryINFO);
                INFO.setString(1,user.getLogin());
                DbRunner.resultSet = INFO.executeQuery();
                if (DbRunner.resultSet.next()){
                    int id = DbRunner.resultSet.getInt(1);
                    String login = DbRunner.resultSet.getString(2);
                    String first_name = DbRunner.resultSet.getString(3);
                    String last_name = DbRunner.resultSet.getString(4);
                    String email = DbRunner.resultSet.getString(5);
                    System.out.printf("ID: %d,\n Login: %s,\n Name: %s,\n Last_name: %s,\n Email: %s \n", id, login, first_name, last_name, email);
                }
                System.out.println(" :) :) :) :) ");
            } else {
                System.out.println("Something went wrong :(");
            }
            reader.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
