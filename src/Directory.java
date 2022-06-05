import java.util.ArrayList;

public class Directory {
    private String directoryName;
    private String directoryPath;
    private ArrayList<File> files;


    private ArrayList<Directory> subDirectories;
    private boolean deleted = false;
    public Directory(){
        files = new ArrayList<>();
        subDirectories = new ArrayList<>();
    }



    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    public ArrayList<Directory> getSubDirectories() {
        return subDirectories;
    }

    public void setSubDirectories(ArrayList<Directory> subDirectories) {
        this.subDirectories = subDirectories;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public void addFile(File file){
        files.add(file);
    }
    public void addDirectory(Directory directory){
        subDirectories.add(directory);
    }
    public void printDirectoryStructure(int level){
        if(this.deleted)
            return;

        for (int i=0;i<level;i++)
            System.out.print("    ");

        System.out.println(directoryName);
        int newLevel= level+1;

        for(int i=0;i<files.size();i++){
            if(files.get(i).isDeleted())
                    continue;
            for(int j=0;j<newLevel;j++)
                System.out.print("    ");
            System.out.println(files.get(i).getFileName());
        }
        for (Directory directory:subDirectories) {
            directory.printDirectoryStructure(newLevel);
        }
    }
}
