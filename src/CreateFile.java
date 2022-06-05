public class CreateFile extends Command{


    public CreateFile(Directory root, String[] splitedPath, String[] splitedCommand) {
        super(root, splitedPath, splitedCommand);
    }

    @Override
    void execute() {
        if(savePathToCreateFile(root,splitedPath,1)){
           File file = new File(splitedPath[splitedPath.length-1],splitedPath[1]);
            file.setFilePath(splitedCommand[1]);
            if(!DiskManager.allocate(file, Integer.parseInt(splitedCommand[2]))){
                System.out.println("No enough space");
            }
            addFileToDirectory(root,splitedPath,file,1);
        }
        else System.out.println("couldn't create file");
    }
    public static boolean savePathToCreateFile(Directory root,String[] pathToCheck,int index){

        if(index== pathToCheck.length-1){
            for(int i = 0;i<root.getFiles().size();i++){
                if(!(root.getFiles().get(i).isDeleted())){
                    if(root.getFiles().get(i).getFileName().equals(pathToCheck[index]))return false;
                }
            }
            return true;
        }
        for(int i = 0;i<root.getSubDirectories().size();i++){
            if(!(root.getSubDirectories().get(i).isDeleted())){
                if(root.getSubDirectories().get(i).getDirectoryName().equals(pathToCheck[index])){
                    return savePathToCreateFile(root.getSubDirectories().get(i),pathToCheck,index+1);
                }
            }


        }
        return false;
    }
    public static void addFileToDirectory(Directory root, String[] pathToParent,File file ,int index){
        if(index== pathToParent.length-1){
            root.addFile(file);
            return;
        }
        for(int i = 0;i<root.getSubDirectories().size();i++){
            if(!(root.getSubDirectories().get(i).isDeleted())){
                if(root.getSubDirectories().get(i).getDirectoryName().equals(pathToParent[index])){
                    addFileToDirectory(root.getSubDirectories().get(i),pathToParent,file,index+1);
                }


            }

        }
    }
}
