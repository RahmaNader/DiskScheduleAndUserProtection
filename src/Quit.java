import java.io.FileWriter;
import java.io.IOException;

public class Quit extends Command{
    User logged;
    Grant grant;
    public Quit(Directory root, String[] splitedCommand,User logged) {
        super(root, splitedCommand);
        this.logged=logged;
    }

    @Override
    void execute() {
        try {
            FileWriter fileWriter = new FileWriter("DiskStructure.txt");
            for (int i=0;i<DiskManager.getFreeBlocks().size();i++)
                fileWriter.write(DiskManager.getFreeBlocks().get(i)+" ");
            fileWriter.write("\n");

            for (int i=0;i<DiskManager.getAllocatedBlocks().size();i++)
                fileWriter.write(DiskManager.getAllocatedBlocks().get(i)+" ");
            fileWriter.write("\n");
            Utility.writeDirectoryStructure(root,fileWriter);
            fileWriter.close();
            fileWriter = new FileWriter("Capabilities.txt");
            FileWriter finalFileWriter = fileWriter;
           Grant.folderToUsers.forEach((k, v) -> {
                try {
                    finalFileWriter.write(k+","+v+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            finalFileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
