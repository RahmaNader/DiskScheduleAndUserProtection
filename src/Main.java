import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static HashMap<String, User> mp = new HashMap<>();
    public static void main(String[] args) {
       mp = CUser.initializeMap();
        User logged = User.initializeAdmin();
        mp.put("admin",logged);
        Directory rootDirectory = new Directory();
        Utility.readFile(rootDirectory);
        Utility.readCapabilitiesFile();
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        while (true){
            System.out.println("enter your command");
            String command1 = scanner.next();
            String [] splitedCommand = command1.split(" ");
            String [] splitedPath=null;
            if(splitedCommand.length>1)
                splitedPath = splitedCommand[1].split("/");
            Command command = null;
            if(command1.startsWith("CreateFile")){
                boolean hasAccess = false;
                System.out.println(logged.map.get(splitedCommand[1]));
                if(logged != null){
                    for (Map.Entry<String, String> set : logged.getMap().entrySet()) {
                        if(splitedCommand[1].contains(set.getKey()) && set.getValue().charAt(0)=='1'){
                            hasAccess = true;
                            break;
                        }
                    }
                }
//Grant Yara root/folder1 10
//CreateFile root/folder1/test.txt 2
                if(hasAccess){
                    System.out.println("Which allocation technique do you want to use?\n1-Contiguous\n2-Linked\n3-Indexed");
                    int choice = scanner.nextInt();
                    DiskManager.setAllocationTechnique(choice == 1 ? new ContiguousAllocation() :
                            choice == 2 ? new Linked() :
                                    choice == 3 ? new Indexed() : null);
                    command=new CreateFile(rootDirectory,splitedPath,splitedCommand);
                }else{
                    System.out.println("current Logged user doesnt have access to create such file");
                    continue;
                }

            }
            else if(command1.startsWith("CreateFolder")){
                if(logged.isAdmin()) {
                    command = new CreateFolder(rootDirectory, splitedPath, splitedCommand);
                }
                else {
                    System.out.println("you aren't an admin\n");
                    continue;
                }

            }

            else if(command1.startsWith("DeleteFile")){
                boolean hasAccess = false;
                if(logged != null){
                    for (Map.Entry<String, String> set : logged.getMap().entrySet()) {
                        if(splitedCommand[1].contains(set.getKey()) && set.getValue().charAt(1)=='1'){
                            hasAccess = true;
                            break;
                        }
                    }
                }
                if(hasAccess)command=new DeleteFile(rootDirectory,splitedPath,splitedCommand);
                else{
                    System.out.println("current Logged user doesnt have access to delete such file");
                    continue;
                }
            }


            else if(command1.startsWith("DeleteFolder")) {
                boolean hasAccess = false;
                if(logged != null){
                    for (Map.Entry<String, String> set : logged.getMap().entrySet()) {
                        if(splitedCommand[1].contains(set.getKey()) && set.getValue().charAt(1)=='1'){
                            hasAccess = true;
                            break;
                        }
                    }
                }
                if(hasAccess)command = new DeleteFolder(rootDirectory, splitedPath, splitedCommand);
                else {
                    System.out.println("current Logged user doesnt have access to delete such folder");
                    continue;
                }
            }

            else if(command1.startsWith("DisplayDiskStructure"))
                command=new DisplayDiskStructure(rootDirectory,splitedCommand);

            else if(command1.startsWith("DisplayDiskStatus"))
                command=new DisplayDiskStatus(rootDirectory,splitedCommand);

            else if(command1.startsWith("CUser")){
                if(logged!=null) {
                    if (logged.getUserName().equals("admin")&&logged.getPassWord().equals("admin")) {
                        command = new CUser(splitedCommand[1], splitedCommand[2]);
                        mp.put(splitedCommand[1],new User(splitedCommand[1],splitedCommand[2]) );
                    } else {
                        System.out.println("You're not an admin");
                        continue;
                    }
                }
                else{
                    System.out.println("please log in to continue");
                    continue;
                }
            }
            else if(command1.startsWith("Login")){
                if(mp.containsKey(splitedCommand[1]))
                    logged = mp.get(splitedCommand[1]);
                else System.out.println("not created user");

                continue;
            }
            else if(command1.startsWith("TellUser")){
                if(logged!=null) {
                    System.out.println(logged.getUserName());
                }
                else System.out.println("No user logged in, please login to continue");
                continue;
            }
            else if(command1.startsWith("Grant")){
                if(logged.isAdmin() && !splitedCommand[2].contains("."))
                        command = new Grant(rootDirectory,splitedPath,splitedCommand);
            }
            else if(command1.equals("quit")){
                command=new Quit(rootDirectory,splitedCommand,logged);
                command.execute();
                break;
            }
            else{
                System.out.println("please enter a valid command");
                continue;
            }
            command.execute();
        }
    }
}