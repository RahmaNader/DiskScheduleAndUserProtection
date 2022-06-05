public class CreateFolder extends Command{
    public CreateFolder(Directory root, String[] splitedPath, String[] splitedCommand) {
        super(root, splitedPath, splitedCommand);
    }

    @Override
    void execute() {
        if(savePathToCreateDir(root,splitedPath,1)){
           Directory directory = new Directory();
            directory.setDirectoryPath(splitedCommand[1]);
            directory.setDirectoryPath(splitedCommand[1]);
            directory.setDirectoryName(splitedPath[splitedPath.length-1]);
            addDirToDirectory(root,splitedPath,directory,1);
            System.out.println("created "+directory.getDirectoryName());
        } else System.out.println("couldn't create directory ");

    }
    public static boolean savePathToCreateDir(Directory root,String[] pathToCheck,int index){
        if(index== pathToCheck.length-1){
            for(int i = 0;i<root.getSubDirectories().size();i++){
                if(!(root.getSubDirectories().get(i).isDeleted())){
                    if(root.getSubDirectories().get(i).getDirectoryName().equals(pathToCheck[index]))return false;
                }
            }
            return true;
        }
        for(int i = 0;i<root.getSubDirectories().size();i++){
            if(!(root.getSubDirectories().get(i).isDeleted())){
                if(root.getSubDirectories().get(i).getDirectoryName().equals(pathToCheck[index])){
                    return savePathToCreateDir(root.getSubDirectories().get(i),pathToCheck,index+1);
                }
            }


        }
        return false;

    }
    public static void addDirToDirectory(Directory root, String[] pathToParent,Directory directory ,int index){
        if(index== pathToParent.length-1){
            root.addDirectory(directory);
            return;
        }
        for(int i = 0;i<root.getSubDirectories().size();i++){
            if(!(root.getSubDirectories().get(i).isDeleted())){
                if(root.getSubDirectories().get(i).getDirectoryName().equals(pathToParent[index])){
                    addDirToDirectory(root.getSubDirectories().get(i),pathToParent,directory,index+1);
                }


            }

        }
    }

}
