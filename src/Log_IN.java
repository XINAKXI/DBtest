import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Log_IN {
    public static void Login(User user) {
        String queryToLogIn = """
                SELECT users.login, passwords.password\s
                FROM users JOIN passwords\s
                ON passwords.user_id = users.id\s
                WHERE users.login = (?) OR passwords.password = (?)""";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your :\n LOGIN and PASSWORD");
        try {
            user.setLogin(reader.readLine());
            user.setPassword(reader.readLine());
            PreparedStatement preparedStatement = DbRunner.connection.prepareStatement(queryToLogIn);
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            DbRunner.resultSet = preparedStatement.executeQuery();
            if (!DbRunner.resultSet.next()) {
                System.out.println("Result is empty");
            } else {
                if (!DbRunner.resultSet.getString("login").equals(user.getLogin())
                        | !DbRunner.resultSet.getString("password").equals(user.getPassword())) {
                    System.out.println("Wrong LOGIN OR PASSWORD!");
                } else {
                    System.out.println("Welcome "+ user.getLogin()+"!");
                    UserInfo.Info(user,reader);
                }
            }
            DbRunner.resultSet.close();
            reader.close();

        } catch (IOException | SQLException e) {
            System.out.println("Something went wrong :(");
            e.printStackTrace();
        }
    }
}
