import java.util.ArrayList;
import java.util.Arrays;

public class DeleteFile extends Command{
    public DeleteFile(Directory root, String[] splitedPath, String[] splitedCommand) {
        super(root, splitedPath, splitedCommand);
    }

    @Override
    void execute() {
        ArrayList<String> splittedPath = new ArrayList<>(Arrays.asList(splitedPath));
        if(DiskManager.deAllocate(root,splittedPath))System.out.println("file deleted successfully");
        else System.out.println("not correct path to file");
    }
}
