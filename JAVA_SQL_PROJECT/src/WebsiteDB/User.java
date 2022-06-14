package WebsiteDB;

public class User {

    public int userId;
    public String name;
    public String email;
    public String gender;
    public String surname;
    public String birthday;
    public String password;
    public String phoneNumber;

    public User(int userId, String name, String surname, String email, String password, String gender, String phoneNumber, String birthday){
        this.name         =  name;
        this.email        =  email;
        this.userId       =  userId;
        this.gender       =  gender;
        this.surname      =  surname;
        this.birthday     =  birthday;
        this.password     =  password;
        this.phoneNumber  =  phoneNumber;

    }

    public User(String name, String surname, String email, String password, String gender, String phoneNumber, String birthday){
        this.name         =  name;
        this.email        =  email;
        this.gender       =  gender;
        this.surname      =  surname;
        this.birthday     =  birthday;
        this.password     =  password;
        this.phoneNumber  =  phoneNumber;

    }

}
