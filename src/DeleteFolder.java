import java.util.ArrayList;
import java.util.Arrays;

public class DeleteFolder extends Command{
    public DeleteFolder(Directory root, String[] splitedPath, String[] splitedCommand) {
        super(root, splitedPath, splitedCommand);
    }

    @Override
    void execute() {
        ArrayList<String> splittedPath = new ArrayList<>(Arrays.asList(splitedCommand[1].split("/")));
        Directory d = getDirToDelete(root,splittedPath);
        if(d==null)System.out.println("no such directory path exist");
        else {
            deleteFolder(d);
        }
    }
    public static Directory getDirToDelete(Directory root,ArrayList<String> path){
        if(path.size()==2){
            for (int i = 0; i<root.getSubDirectories().size();i++){
                if(!(root.getSubDirectories().get(i).isDeleted())){
                    if(root.getSubDirectories().get(i).getDirectoryName().equals(path.get(1)))return root.getSubDirectories().get(i);
                }

            }
            return null;
        }

        for (int i = 0; i<root.getSubDirectories().size();i++){
            if(!(root.getSubDirectories().get(i).isDeleted())){
                if(root.getSubDirectories().get(i).getDirectoryName().equals(path.get(1))){
                    path.remove(0);
                    return getDirToDelete(root.getSubDirectories().get(i),path);
                }

            }
        }

        return null;

    }
    public static void deleteFolder(Directory folderToDelete){
        folderToDelete.setDeleted(true);
        for (int i = 0;i<folderToDelete.getFiles().size();i++){
            if(!folderToDelete.getFiles().get(i).isDeleted()){
                for(int j = 0;j<folderToDelete.getFiles().get(i).getAllocatedBlocks().size();j++){
                    DiskManager.blocks.set(folderToDelete.getFiles().get(i).getAllocatedBlocks().get(j),0);
                }
                folderToDelete.getFiles().get(i).setDeleted(true);
                folderToDelete.getFiles().get(i).getAllocatedBlocks().clear();
            }
        }
        for (int i = 0; i<folderToDelete.getSubDirectories().size();i++){
            deleteFolder(folderToDelete.getSubDirectories().get(i));
        }
    }
}
