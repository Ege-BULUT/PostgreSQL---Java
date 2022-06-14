package WebsiteDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class mainOperations {

    public static List<User> tableList = new ArrayList<User>();

    public static void main(String[] args) throws SQLException {

        String query = "SELECT * FROM public.\"user\" ;";
        DataOperation dataOP = new DataOperation();
        ResultSet rs = dataOP.selectOperation(query);

        while (rs.next()) {
            int userid = rs.getInt("userid");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String gender = rs.getString("gender");
            String surname = rs.getString("surname");
            String password = rs.getString("password");
            String birthday = rs.getString("birthday");
            String phoneNumber = rs.getString("phone_number");

            User user = new User(userid, name, surname, email, password, gender, phoneNumber, birthday);

            tableList.add(user);


            System.out.println("\nid      \t: " + userid +
                    "\nname    \t: " + name +
                    "\nsurname \t: " + surname +
                    "\ngender  \t: " + gender +
                    "\nemail   \t: " + email +
                    "\npassword\t: " + password +
                    "\nbirthday\t: " + birthday);
        }
        System.out.println("User miktarı : " + tableList.size());
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    System.out.print("\nid          \t: ");
                    break;
                case 1:
                    System.out.print("name        \t: ");
                    break;
                case 2:
                    System.out.print("surname     \t: ");
                    break;
                case 3:
                    System.out.print("gender      \t: ");
                    break;
                case 4:
                    System.out.print("email       \t: ");
                    break;
                case 5:
                    System.out.print("password    \t: ");
                    break;
                case 6:
                    System.out.print("phone number\t: ");
                    break;
                case 7:
                    System.out.print("BirthDay    \t: ");
                    break;
            }
            for (User value : tableList) {
                switch (i) {
                    case 0:
                        System.out.printf("%-30s %s", value.userId, " ");
                        break;
                    case 1:
                        System.out.printf("%-30s %s", value.name, " ");
                        break;
                    case 2:
                        System.out.printf("%-30s %s", value.surname, " ");
                        break;
                    case 3:
                        System.out.printf("%-30s %s", value.gender, " ");
                        break;
                    case 4:
                        System.out.printf("%-30s %s", value.email, " ");
                        break;
                    case 5:
                        System.out.printf("%-30s %s", value.password, " ");
                        break;
                    case 6:
                        System.out.printf("%-30s %s", value.phoneNumber, " ");
                        break;
                    case 7:
                        System.out.printf("%-30s %s", value.birthday, " ");
                        break;

                }
            }
            System.out.print("\n");
        }

        System.out.println("________\n");
        System.out.printf( "%-20s %-20s %-20s %-20s %-35s %-20s %-20s %s\n","id","name","surname","gender","email","password","phone number","birthday");
        for (User value : tableList) {
            System.out.printf("%-20s %-20s %-20s %-20s %-35s %-20s %-20s %s\n",
                    value.userId,
                    value.name,
                    value.surname,
                    value.gender,
                    value.email,
                    value.password,
                    value.phoneNumber,
                    value.birthday);
        }
    }

    @Override
    public String toString() {
        StringBuffer user = new StringBuffer("");
        System.out.println("User miktarı : " + tableList.size());
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    System.out.print("\nid          \t: ");
                    break;
                case 1:
                    System.out.print("name        \t: ");
                    break;
                case 2:
                    System.out.print("surname     \t: ");
                    break;
                case 3:
                    System.out.print("gender      \t: ");
                    break;
                case 4:
                    System.out.print("email       \t: ");
                    break;
                case 5:
                    System.out.print("password    \t: ");
                    break;
                case 6:
                    System.out.print("phone number\t: ");
                    break;
                case 7:
                    System.out.print("BirthDay    \t: ");
                    break;
            }
            for (User value : tableList) {
                switch (i) {
                    case 0:
                        System.out.printf("%-30s %s", value.userId, " ");
                        break;
                    case 1:
                        System.out.printf("%-30s %s", value.name, " ");
                        break;
                    case 2:
                        System.out.printf("%-30s %s", value.surname, " ");
                        break;
                    case 3:
                        System.out.printf("%-30s %s", value.gender, " ");
                        break;
                    case 4:
                        System.out.printf("%-30s %s", value.email, " ");
                        break;
                    case 5:
                        System.out.printf("%-30s %s", value.password, " ");
                        break;
                    case 6:
                        System.out.printf("%-30s %s", value.phoneNumber, " ");
                        break;
                    case 7:
                        System.out.printf("%-30s %s", value.birthday, " ");
                        break;

                }
            }
            System.out.print("\n");
        }
        return "";
    }
}
