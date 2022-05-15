package PostgreSQL;

//STEP 1. Import required packages
import Security.LoginController;
import com.company.Database;
import com.company.Person;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class DatabaseManager {
    //  Database credentials
    static final String DB_URL = "/jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "123";
    Connection c = null;
    Statement stmt;
    private boolean connectionEstablished = false;

    public void initialize(){
        try {
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "123");
            connectionEstablished = true;
            stmt = c.createStatement();
        } catch (SQLException e){
            System.out.println("Unable to connect to database");
            connectionEstablished = false;
        }
    }


    public void createNewUserBase() {
        try {
            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE USERBASE " +
                    "(ID SERIAL PRIMARY KEY     NOT NULL, " +
                    " USER1           TEXT    NOT NULL, " +
                    " NAME1          TEXT    NOT NULL, " +
                    " COORDINATEX    INT     NOT NULL, " +
                    " COORDINATEY    FLOAT   NOT NULL, " +
                    " CREATIONDATE   TEXT    NOT NULL, " +
                    " HEIGHT         TEXT    NOT NULL, " +
                    " EYECOLOR       TEXT    NOT NULL, " +
                    " HAIRCOLOR      TEXT    NOT NULL, " +
                    " NATIONALITY    TEXT    NOT NULL, " +
                    " LOCATIONX      TEXT    NOT NULL, " +
                    " LOCATIONY      FLOAT   NOT NULL, " +
                    " LOCATIONZ      FLOAT   NOT NULL)";
            stmt.executeUpdate(sql);
            //stmt.close();
        } catch (PSQLException e){
            System.out.println("Database located");
        } catch (SQLException e){
            System.out.println("Unable to create database");
        }
    }

    public void createNewPasswordBase(){
        try {
            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE PASSWORDBASE " +
                    "(ID SERIAL PRIMARY KEY     NOT NULL, " +
                    " USERNAME           TEXT    NOT NULL, " +
                    " PASSWORD           TEXT    NOT NULL);";
            stmt.executeUpdate(sql);
        } catch (PSQLException e){
            System.out.println("Database located");
        } catch (SQLException e){
            System.out.println("Unable to create database");
        }
    }

    public boolean userExists(LoginController loginController){
        boolean res = false;
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM passwordbase");
            while(rs.next()){
                if(rs.getString("USERNAME").equals(loginController.getLogin())){
                    res = true;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public boolean passwordMatches(String password ,String login){
        boolean res = false;
        try{
            ResultSet rs = stmt.executeQuery("SELECT PASSWORD FROM passwordbase WHERE USERNAME = '"+login+"';");
            rs.next();
            String result = rs.getString("PASSWORD");
            System.out.println(result);
            System.out.println(password);
            if(password.equals(result))
                res = true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public void addNewUser(LoginController loginController){
        try{
            String sql = "INSERT INTO PASSWORDBASE (USERNAME, PASSWORD) VALUES ('" + loginController.getLogin()+"', '" + loginController.getPassword()+ "'); ";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void readCollectionFromDatabase(Database database){
        try{
        database.getCollection().clear();
        ResultSet rs = stmt.executeQuery("SELECT * FROM userbase");
        while (rs.next()) {
            Person person = new Person(rs.getLong("id"), rs.getString("user1"), rs.getString("name1"),
                    rs.getInt("coordinatex"), rs.getFloat("coordinatey"), new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(rs.getString("creationdate")),
                    rs.getInt("height"), Person.Color.valueOf(rs.getString("eyecolor").toUpperCase(Locale.ROOT)), Person.Color.valueOf(rs.getString("haircolor").toUpperCase(Locale.ROOT)),
                    Person.Country.valueOf(rs.getString("nationality").toUpperCase(Locale.ROOT)), rs.getLong("locationx"),
                    rs.getFloat("locationy"), rs.getFloat("locationz"));
            System.out.println(person);
            database.getCollection().add(person);
        }
        } catch (SQLException | ParseException e){
            e.printStackTrace();
        }
    }

    public void addElementToDatabase(Person person){
        try {

            String sql = "INSERT INTO USERBASE (USER1, NAME1, COORDINATEX,COORDINATEY, CREATIONDATE, HEIGHT, " +
                    "EYECOLOR, HAIRCOLOR, NATIONALITY, LOCATIONX ,LOCATIONY, LOCATIONZ ) VALUES ('" +
                    person.getUserName() + "','" + person.getName() + "','" + person.getCoordinates().getX() + "','" + person.getCoordinates().getY() + "','" +
                    person.getCreationDate().toString() + "','" + person.getHeight() + "','" + person.getEyeColor() + "','" + person.getHairColor() + "','" +
                    person.getNationality() + "','" + person.getLocation().getX() + "','" + person.getLocation().getY() + "','" + person.getLocation().getZ() + "');";
            stmt.executeUpdate(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }


    }


    public static void execute(){

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "123");
            System.out.println("Opened database successfully");
            /*
            stmt = c.createStatement();
            String sql = "CREATE TABLE USERBASE " +
                    "(ID SERIAL PRIMARY KEY     NOT NULL, " +
                    " USER1           TEXT    NOT NULL, " +
                    " NAME1          TEXT    NOT NULL, " +
                    " COORDINATEX    INT     NOT NULL, " +
                    " COORDINATEY    FLOAT   NOT NULL, " +
                    " CREATIONDATE   TEXT    NOT NULL, " +
                    " HEIGHT         TEXT    NOT NULL, " +
                    " EYECOLOR       TEXT    NOT NULL, " +
                    " HAIRCOLOR      TEXT    NOT NULL, " +
                    " NATIONALITY    TEXT    NOT NULL, " +
                    " LOCATIONX      TEXT    NOT NULL, " +
                    " LOCATIONY      FLOAT   NOT NULL, " +
                    " LOCATIONZ      FLOAT   NOT NULL)";

            stmt.executeUpdate(sql);
            stmt.close();
            */

            stmt = c.createStatement();
            String sql = "INSERT INTO USERBASE (USER1, NAME1, COORDINATEX,COORDINATEY, CREATIONDATE, HEIGHT, " +
                    "EYECOLOR, HAIRCOLOR, NATIONALITY, LOCATIONX ,LOCATIONY, LOCATIONZ ) "
                    + "VALUES ('Paul', 'name2', 1,3,'05.03.2003', 66, 'red', 'red', 'usa', 32, 20000.00, 2202 );";
            //sql = "ALTER TABLE userbase ADD COLUMN id SERIAL;";
            sql = "SELECT * FROM userbase;";
            ResultSet rs = stmt.executeQuery("SELECT * FROM userbase");
            while (rs.next()){
                System.out.println(rs.getString("user1"));
                //System.out.println(rs.getString("user1"));
            }

            //ResultSet rs = stmt.executeUpdate(sql);
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}
