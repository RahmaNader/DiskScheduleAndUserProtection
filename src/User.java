import java.util.HashMap;

public class User {
    private String userName;
    private String passWord;
    private static User admin;
    private boolean isAdmin;
    HashMap<String, String> map = new HashMap<String, String>();

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        this.isAdmin = false;
    }
    public static User initializeAdmin(){
        admin = new User("admin","admin");
        admin.isAdmin = true;
        return admin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public void addToMap(String folderPath,String capability){
        map.put(folderPath, capability);
    }
}

