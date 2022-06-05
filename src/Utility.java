import java.io.*;
import java.util.*;

public final class Utility {
    public static File CreateFile(Directory root,ArrayList<String> path) {
        if (path.size() == 2) {
            File file = new File();
            root.getFiles().add(file);
            return file;
        }
        for (int i = 0; i < root.getSubDirectories().size(); i++) {
            if (root.getSubDirectories().get(i).getDirectoryName().equals(path.get(1))) {
                path.remove(0);
                return CreateFile(root.getSubDirectories().get(i), path);
            }
        }
        return null;
    }
    public static Directory CreateFolder(Directory root,ArrayList<String> path) {
        if (path.size() == 2) {
            Directory directory = new Directory();
            root.getSubDirectories().add(directory);
            return directory;
        }
        for (int i = 0; i < root.getSubDirectories().size(); i++) {
            if (root.getSubDirectories().get(i).getDirectoryName().equals(path.get(1))) {
                path.remove(0);
                return CreateFolder(root.getSubDirectories().get(i), path);
            }
        }
        return null;
    }
    public static void readFile(Directory rootDirectory) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("DiskStructure.txt"));
            String emptyBlocks = reader.readLine();
            if(emptyBlocks==null){
                rootDirectory.setDirectoryName("root");
                rootDirectory.setDirectoryPath("root");
                return;
            }
            String[] blocks = emptyBlocks.split(" ");
            File file;
            for (int i = 0; i < blocks.length; i++)
                DiskManager.getBlocks().set(Integer.parseInt(blocks[i]), 0);
            String allocatedBlocks = reader.readLine();
            String[] ablocks = allocatedBlocks.split(" ");
            for (int i = 0; i < ablocks.length; i++)
                DiskManager.getBlocks().set(Integer.parseInt(ablocks[i]), 1);
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String []splitedline = line.split(" ");
                ArrayList<String> splittedPath = new ArrayList<>(Arrays.asList(splitedline[0].split("/")));
                if(line.contains(".")){
                    file= CreateFile(rootDirectory,splittedPath);
                    file.setFileName(splittedPath.get(splittedPath.size()-1));
                    file.setFilePath(splitedline[0]);
                    for (int i=1;i<splitedline.length;i++){
                        file.getAllocatedBlocks().add(Integer.parseInt(splitedline[i]));
                        DiskManager.getBlocks().set(Integer.parseInt(splitedline[i]),1);
                    }


                }
                else{
                    if(line.equals("root ")){
                        rootDirectory.setDirectoryName("root");
                        rootDirectory.setDirectoryPath("root");
                    }
                    else {
                        Directory directory = CreateFolder(rootDirectory,splittedPath);
                        directory.setDirectoryName(splittedPath.get(splittedPath.size()-1));
                        directory.setDirectoryPath(splitedline[0]);
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void writeDirectoryStructure(Directory root, FileWriter fileWriter) {
        if (root.isDeleted())
            return;
        try {
            if(root.getDirectoryPath()==null){
                fileWriter.write("root" +"\n");
            }
            else
                fileWriter.write(root.getDirectoryPath()+ " " +"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        for (int i = 0; i < root.getFiles().size(); i++) {
            if (root.getFiles().get(i).isDeleted())
                continue;

            try {
                fileWriter.write(root.getFiles().get(i).getFilePath() + " ");
                for (int index : root.getFiles().get(i).getAllocatedBlocks())
                    fileWriter.write(index + " ");
                fileWriter.write("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (Directory directory : root.getSubDirectories()) {
            writeDirectoryStructure(directory,fileWriter);
        }
    }
    public static void readCapabilitiesFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Capabilities.txt"));
            for (String line=reader.readLine();line!=null;line=reader.readLine()){
                String [] elements=line.split(",");
                for(int i=1;i<elements.length;i+=2){
                    User user=Main.mp.get(elements[i]);
                    user.getMap().put(elements[0],elements[i+1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
