package Security;

import java.io.Serializable;
import java.util.Locale;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginController implements Serializable {
    private String login;
    private String password;
    private boolean newUser;
    private boolean approved = false;

    public void login(){
        System.out.println("Type 'sign up', if you are a new user and 'sign in', if you have an account");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if(line.toLowerCase(Locale.ROOT).equals("sign up")) {
            newUser = true;
            System.out.print("Enter your login: ");
            login = scanner.nextLine();
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            password = get_SHA_512_SecurePassword(password,"PeregudinTop666");
        }
        else {
            if (line.toLowerCase(Locale.ROOT).equals("sign in")) {
                newUser = false;
                System.out.print("Enter your login: ");
                login = scanner.nextLine();
                System.out.print("Enter your password: ");
                password = scanner.nextLine();
                password = get_SHA_512_SecurePassword(password, "PeregudinTop666");
            }
            else{
                login();
            }
        }


    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String get_SHA_512_SecurePassword(String passwordToHash, String salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public boolean isNew(){
        return newUser;
    }

    public void setApproved(boolean approved){
        this.approved = approved;
    }

    public boolean isApproved(){
        return approved;
    }
}