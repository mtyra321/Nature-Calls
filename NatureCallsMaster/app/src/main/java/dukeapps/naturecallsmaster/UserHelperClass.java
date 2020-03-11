package dukeapps.naturecallsmaster;

public class UserHelperClass {

    String signName, userName, password, vPassword;

    public UserHelperClass() {
    }

    public UserHelperClass(String signName, String userName, String password, String vPassword) {
        this.signName = signName;
        this.userName = userName;
        this.password = password;
        this.vPassword = vPassword;
    }

    public String getSignName() {
        return signName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getvPassword() {
        return vPassword;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setvPassword(String vPassword) {
        this.vPassword = vPassword;
    }
}
