import java.io.*;
import java.util.*;

public class UserService {

    private static final String FILE = "users.csv";

    public static boolean userExists(String username) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data[0].equals(username)) {
                br.close();
                return true;
            }
        }
        br.close();
        return false;
    }

    public static void register(User user) throws IOException {
        if (userExists(user.getUsername())) {
            System.out.println("User already exists!");
            return;
        }

        FileWriter fw = new FileWriter(FILE, true);
        fw.write(user.toCSV() + "\n");
        fw.close();
        System.out.println("User Registered!");
    }

    public static User login(String username, String password) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE));
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data[0].equals(username) && data[1].equals(password)) {
                br.close();
                return new User(data[0], data[1], data[2]);
            }
        }
        br.close();
        return null;
    }

    public static void viewUsers() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE));
        String line;
        System.out.println("\nAll Users:");
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    public static void deleteUser(String username) throws IOException {
        File input = new File(FILE);
        File temp = new File("temp.csv");

        BufferedReader br = new BufferedReader(new FileReader(input));
        BufferedWriter bw = new BufferedWriter(new FileWriter(temp));

        String line;
        while ((line = br.readLine()) != null) {
            if (!line.startsWith(username + ",")) {
                bw.write(line + "\n");
            }
        }

        br.close();
        bw.close();
        input.delete();
        temp.renameTo(input);

        System.out.println("User deleted!");
    }
}