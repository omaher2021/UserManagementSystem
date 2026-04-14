import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("\n1. Register\n2. Login\n3. Exit");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Username: ");
                    String u = sc.nextLine();

                    System.out.print("Password: ");
                    String p = sc.nextLine();

                    if (!Validator.isValidUsername(u) || !Validator.isValidPassword(p)) {
                        System.out.println("Invalid input!");
                        continue;
                    }

                    System.out.print("Role (admin/user): ");
                    String role = sc.nextLine();

                    UserService.register(new User(u, p, role));

                } else if (choice == 2) {
                    System.out.print("Username: ");
                    String u = sc.nextLine();

                    System.out.print("Password: ");
                    String p = sc.nextLine();

                    User user = UserService.login(u, p);

                    if (user != null) {
                        System.out.println("Login Successful!");

                        if (user.getRole().equals("admin")) {
                            System.out.println("1. View Users\n2. Delete User");
                            int ch = sc.nextInt();
                            sc.nextLine();

                            if (ch == 1) UserService.viewUsers();
                            else if (ch == 2) {
                                System.out.print("Enter username to delete: ");
                                String del = sc.nextLine();
                                UserService.deleteUser(del);
                            }
                        }
                    } else {
                        System.out.println("Invalid credentials!");
                    }

                } else break;

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}