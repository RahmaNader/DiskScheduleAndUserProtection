import java.io.*;
import java.io.File;
import java.util.HashMap;

public class CUser extends Command {
    static File userTxt;

    public CUser(String userName, String passWord) {
        super(userName, passWord);
    }

    public static void initializeFile(){
        try {
            userTxt = new File("user.txt");
            userTxt.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public boolean userExists(String userName) {
        boolean state = false;
        try {
            FileReader fr=new FileReader(userTxt);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            while ((line = br.readLine()) != null) {
                String [] split = line.split(", ");
                if(split[0].equals(userName)) {
                    return true;
                }
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return state;
    }

    public static HashMap<String,User> initializeMap() {
        initializeFile();
        HashMap<String,User> mp = new HashMap<>();
        try {
            FileReader fr=new FileReader(userTxt);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            while ((line = br.readLine()) != null) {
                String [] split = line.split(", ");
                User u = new User(split[0],split[1]);
                mp.put(split[0],u);
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mp;
    }
    public void insertToFile(String userName, String passWord) {
        try {
            String data = userName + ", " + passWord+"\n";
            FileWriter fileWriter = new FileWriter(userTxt.getName(), true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(data);
            bw.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void execute() {
        initializeFile();
        if(!userExists(this.user.getUserName())){
            insertToFile(this.user.getUserName(),this.user.getPassWord());
        }
        else{
            System.out.println("user name already exists, try another one");
        }
    }
}
