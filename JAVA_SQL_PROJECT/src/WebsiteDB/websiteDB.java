package WebsiteDB;

import javax.swing.plaf.nimbus.State;
import java.nio.file.LinkPermission;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

// port : 5432 || localhost'ta olduğu için ayrıca url'ye eklemeye gerek yok.
// local hostta olmasaydı url'de localhost yazan yere ip ya da websitesi de yazıyor olabilirdik.
// proje.firebase.com gibi ya da 181.153.225.1 de olabilirdi

/*
 * TODO : create new features.
 *   -author : Ege BULUT
 */

public class websiteDB {

    private final  String url           = "jdbc:postgresql://localhost:5432/websiteDB";
    private final  String user          = "postgres";
    private final  String password      = "admin";
    private static Scanner input        = new Scanner(System.in);
    private static DataOperation dataOP;
    private static Connection conn = null;

    public Connection connectToDB(){

        try {

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successfully !");
        } catch (SQLException throwables) {
            System.out.println("Error : \n"+throwables.getMessage() + "\nError Code : "+throwables.getErrorCode());
        }

        return conn;
    }

    public static void main(String[] args) throws SQLException {
        int secim;
        System.out.println("\t*** Alışveriş veritabanı sistemi yönetim programı ***\n");
        System.out.println("1-> Default database'e bağlan\n2-> Custom database'e bağlan");
        secim = input.nextInt();
        String urlName = "websiteDB";
        String url="jdbc:postgresql://localhost/"+urlName;
        String username = "postgres";
        String password = "admin";
        switch (secim){
            case 1 :
                 dataOP = new DataOperation(url, username, password);
                break;
            case 2:
                System.out.println("\ndatabase ip( ya da \"localhost\") : ");
                String urlIP = input.next();
                if(!urlIP.equals("localhost")){
                    System.out.println("Port : ");
                    String urlPort = input.next();
                    url = "jdbc:postgresql://"+urlIP+":"+urlPort;
                }
                else{url = "jdbc:postgresql://"+urlIP;}
                System.out.println("\ndatabase ismi : ");
                urlName = input.next();
                url = url+"/"+urlName;
                System.out.println("\nKullanıcı adı : ");
                username = input.next();
                System.out.println("\nPassword : ");
                password = input.next();
                dataOP = new DataOperation(url, username, password);
                System.out.println("...\n"+urlName+" veritabanına başarıyla bağlanıldı!\n...");
                break;
            default:
                System.out.println("Yanlış rakam tuşladınız!");
                break;
        }
        if(urlName.equals("jdbc:postgresql://localhost/websiteDB")){
            System.out.println("websiteDB Database'indeki tablolar : \n");
        }else{System.out.println(urlName+" Database'indeki tablolar : \n");}
        conn = DriverManager.getConnection(url, username, password);
        DatabaseMetaData md = conn.getMetaData();
        ResultSet rsTableNames = md.getTables(null, "public", "%", new String[]{"TABLE"});
        while (rsTableNames.next()) {
            System.out.println(rsTableNames.getString(3));
        }
        System.out.println("\n1-> SELECT operation\n2-> INSERT operation");
        secim = input.nextInt();
        input.nextLine();//Dummy
        switch (secim){
            case 1 :
                System.out.println("\nSELECT : ");
                String Select = input.nextLine();
                System.out.println("\nFROM : ");
                String From = input.nextLine();
                System.out.println("\nWHERE : (write \"null\" if you doesn't need WHERE)");
                String Where = input.nextLine();
                String query = "";
                if(Where.equals("null")){
                    query = query+"SELECT "+ Select +" FROM "+ From;
                }
                else{
                    query = query+"SELECT "+ Select +"\nFROM "+ From+"\nWHERE "+Where;
                }
                System.out.println("QUERY : \n"+query);
                ResultSet rs = dataOP.selectOperation(query);
                ResultSetMetaData rsmd = rs.getMetaData();
                int counter = 0;
                while (rs.next()){
                    System.out.print("_________\n"+counter+": \n");
                    for (int i = 1; i <= rsmd.getColumnCount(); i++){
                        String name = rsmd.getColumnName(i);
                        String info = String.valueOf(rs.getString(name));
                        System.out.println(name+" : "+info);
                    }
                    counter++;
                }
                break;
            case 2:
                addUser();
                break;
            default:
                System.out.println("Yanlış rakam tuşladınız!");
                break;
        }
    main(null);
    }

    public static void getUsers() throws SQLException {

        websiteDB app = new websiteDB();
        Connection connect = app.connectToDB();
        Statement state = null;
        try {
            state = connect.createStatement();
        } catch (SQLException throwables) {
            Logger.getLogger(websiteDB.class.getName()).log(Level.SEVERE,null, throwables);
        }
        ResultSet rs = null;
        try {
            rs = state.executeQuery("SELECT * FROM public.\"user\" ;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<String> tableList = new LinkedList<String>();
        while (rs.next()){
            int userid = rs.getInt("userid");
            String name = rs.getString("name");
            String email = rs.getString("email");
            tableList.add("id\t\t: "+String.valueOf(userid)+"\nname\t: "+name+"\nemail\t: "+email+"\n");
            System.out.println("user_id : "+userid+"\t| name : "+name+"\t| email : "+email);
        }
        for(int i=0; i<tableList.size(); i++){
            System.out.printf(tableList.get(i));
        }
        rs.close();
    }

    public static void addUser() {
        System.out.println("Kullanıcı eklemek için sırasıyla birer boşluk bırakarak şu bilgileri girin:\nname, surname, password, email, gender, phone_number, birthday(yıl-ay-gün)\n");
        input.nextLine();//dummy
        String userInfo = input.nextLine();
        String[] userInfoArr = userInfo.split(" ");

        websiteDB app = new websiteDB();
        Connection connect = app.connectToDB();
        Statement state = null;

        String query = "INSERT INTO public.\"user\" (name, surname, password, email, gender, phone_number, birthday)" +
                " VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement prpState = connect.prepareStatement(query);
            prpState.setString(1, userInfoArr[0]);
            prpState.setString(2, userInfoArr[1]);
            prpState.setString(3, userInfoArr[2]);
            prpState.setString(4, userInfoArr[3]);
            prpState.setString(5, userInfoArr[4]);
            prpState.setString(6, userInfoArr[5]);
            prpState.setString(7, userInfoArr[6]);
            dataOP.insertOperation(prpState);

            connect.close();
            prpState.close();
            getUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
