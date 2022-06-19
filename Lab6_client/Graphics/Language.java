package Graphics;

public class Language {
    int languageCode = 0;

    public void setLang(String lang){
        switch (lang) {
            case ("Русский"):
                languageCode = 0;
                break;
            case ("Suomalainen"):
                languageCode = 1;
                break;
            case ("Hrvatski"):
                languageCode = 2;
                break;
            case ("English (Ind)"):
                languageCode = 3;
        }
    }
    String [] loginLoc = {"Логин", "Kirjaudu sisään", "prijaviti se", "Login"};
    String [] passwordLoc = {"Пароль", "Salasana", "zaporka", "Password"};
    String [] wrongLoginLoc = {"Проверьте логин и пароль","Tarkista käyttäjätunnuksesi tai salasanasi","Provjerite svoju prijavu ili lozinku","Check your login or password"};
    String [] takenLoginLoc = {"Этот логин уже используется", "Tämä kirjautuminen on jo otettu", "Ova prijava je već zauzeta","This login has already been taken"};
    String [] noServerLoc = {"Сервер не отвечает", "palvelin ei vastaa","poslužitelj ne odgovara", "Server is not responding"};
    String [] signInLoc = {"Войти", "Kirjaudu sisään", "Prijaviti se", "Sign in"};
    String [] signUpLoc = {"Зарегестрироваться", "Kirjaudu" ,"prijavi se","Sign up"};
    public String getLoginLoc() {
        return loginLoc[languageCode];
    }
    public String getPasswordLoc() {
        return passwordLoc[languageCode];
    }

    public String getTakenLoginLoc() {
        return takenLoginLoc[languageCode];
    }

    public String getWrongLoginLoc() {
        return wrongLoginLoc[languageCode];
    }

    public String getNoServerLoc() {
        return noServerLoc[languageCode];
    }

    public String getSignInLoc() {
        return signInLoc[languageCode];
    }

    public String getSignUpLoc() {
        return signUpLoc[languageCode];
    }
}