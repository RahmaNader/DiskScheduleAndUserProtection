import java.util.*;

public class Grant extends Command {
  public static HashMap<String,String> folderToUsers = new HashMap<>();

    public Grant(Directory root, String[] splitedPath, String[] splitedCommand) {
        super(root, splitedPath, splitedCommand);
    }
    // Grant ahmed VFSD:\Folder1 10
    @Override
    void execute() {
        boolean found = false;
        if(Main.mp.containsKey(splitedCommand[1]) && folderExist(root,new ArrayList<>(Arrays.asList(splitedCommand[2].split("/"))))){
            User user = Main.mp.get(splitedCommand[1]);
            user.addToMap(splitedCommand[2],splitedCommand[3]);
            for(Map.Entry<String, String> set : folderToUsers.entrySet()){
                if(Objects.equals(set.getKey(), splitedCommand[2])){
                    String temp = set.getValue();
                    set.setValue(temp+","+user.getUserName()+","+splitedCommand[3]);
                    found=true;
                    break;
                }
            }
            if(!found){
                String temp=splitedCommand[1]+","+splitedCommand[3];
                folderToUsers.put(splitedCommand[2],temp);
            }
        } else System.out.println("could not execute command grant");
    }
    public static boolean folderExist(Directory root, ArrayList<String> path){
        if(path.size()==2){
            for (int i = 0; i<root.getSubDirectories().size();i++){
                if(!(root.getSubDirectories().get(i).isDeleted())){
                    if(root.getSubDirectories().get(i).getDirectoryName().equals(path.get(1))){
                        return true;
                    }
                }

            }
            return false;
        }

        for (int i = 0; i<root.getSubDirectories().size();i++){
            if(!(root.getSubDirectories().get(i).isDeleted())){
                if(root.getSubDirectories().get(i).getDirectoryName().equals(path.get(1))){
                    path.remove(0);
                    return folderExist(root.getSubDirectories().get(i),path);
                }

            }
        }

        return false;

    }
}
