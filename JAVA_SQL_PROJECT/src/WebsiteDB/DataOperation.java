package WebsiteDB;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataOperation {

    private Connection conn;
    private String password = "admin";
    private String user     = "postgres";
    private String url      = "jdbc:postgresql://localhost/postgres";

    public DataOperation(String url, String user, String password){
        this.url      = url;
        this.user     = user;
        this.password = password;

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successfully !");
        } catch (SQLException throwables) {
            System.out.println("Error : \n"+throwables.getMessage() + "\nError Code : "+throwables.getErrorCode());
        }
    }

    public DataOperation(){
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successfully !");
        } catch (SQLException throwables) {
            System.out.println("Error : \n"+throwables.getMessage() + "\nError Code : "+throwables.getErrorCode());
        }
    }

    public ResultSet selectOperation(String query){

        Statement state = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            state = conn.createStatement();
        } catch (SQLException throwables) {
            Logger.getLogger(websiteDB.class.getName()).log(Level.SEVERE,null, throwables);
        }
        ResultSet rs = null;
        try {
            rs = state.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rs;
    }

    public String insertOperation(PreparedStatement prpState){
        try {
            prpState.execute();
            return "Insert into işlemi başarıyla gerçekleşti !";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "BAŞARISIZ ! Insert into sırasında sorun oluştu!";
        }
    }

}
