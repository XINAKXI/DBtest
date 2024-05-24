import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandsFromUser {

    public static void Commands () {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome!\n" +
                "What would you like to do?\n" +
                "Available commands : 'LOG IN' or 'L', 'CREATE NEW ACCOUNT' or 'C'");

        try {
            String com = reader.readLine();

            if (com.equals("LOG IN") || com.equals("L")){
                Log_IN.Login(new User());
            }
            if (com.equals("CREATE NEW ACCOUNT") || com.equals("C")){
                CreatingAnAccount.CreateAcc(new User());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
