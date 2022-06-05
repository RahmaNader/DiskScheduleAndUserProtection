import java.util.ArrayList;

public class File {
    private String fileName;
    private String filePath;
    private ArrayList<Integer> allocatedBlocks;

    private boolean deleted = false;
    public File(){
        allocatedBlocks = new ArrayList<>();
    }

    public File(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
        allocatedBlocks = new ArrayList<>();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Integer> getAllocatedBlocks() {
        return allocatedBlocks;
    }

    public void setAllocatedBlocks(ArrayList<Integer> allocatedBlocks) {
        this.allocatedBlocks = allocatedBlocks;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
