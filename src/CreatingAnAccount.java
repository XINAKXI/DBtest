import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatingAnAccount {
    public static void CreateAcc (User user) {
        String queryCl = "INSERT INTO users (login, firstname, lastname, email) \n" +
                "VALUES (?, ?, ?, ?)";
        String queryPS = "INSERT INTO passwords (password, user_id) VALUES (?, ?)";

        System.out.println("Please enter your :\nLogin\n" +
                "Firstname\n" +
                "Lastname\n" +
                "Email\n" +
                "Password");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try(PreparedStatement pstnforpas = DbRunner.connection.prepareStatement(queryPS);
                PreparedStatement pstn = DbRunner.connection.prepareStatement(queryCl,Statement.RETURN_GENERATED_KEYS)) {

            DbRunner.connection.setAutoCommit(false);

            user.setLogin(reader.readLine());
            user.setFirstname(reader.readLine());
            user.setLastname(reader.readLine());
            user.setEmail(reader.readLine());
            user.setPassword(reader.readLine());
            reader.close();

            pstn.setString(1,user.getLogin());
            pstn.setString(2,user.getFirstname());
            pstn.setString(3,user.getLastname());
            pstn.setString(4, user.getEmail());
            DbRunner.changes = pstn.executeUpdate();

            if (DbRunner.changes == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try(ResultSet generatedKeys = pstn.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUser_id(generatedKeys.getLong("id"));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            pstn.close();

            pstnforpas.setString(1, user.getPassword());
            pstnforpas.setLong(2,user.getUser_id());
            DbRunner.changes = pstnforpas.executeUpdate();

            if (DbRunner.changes == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            } else {
                DbRunner.connection.commit();
                System.out.println("Account created!");
                pstnforpas.close();
            }
        } catch (IOException | SQLException e) {
            if (DbRunner.connection != null) {
                try {
                    DbRunner.connection.rollback();
                } catch (SQLException exception) {
                    System.out.println(exception);
                }
            }
            System.out.println("Account was not created!");
            e.printStackTrace();
        }
    }
}
