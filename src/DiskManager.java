import java.util.ArrayList;
import java.util.Collections;

public final class DiskManager {
   public static ArrayList<Integer> blocks =new ArrayList<>(Collections.nCopies(1000, 0));
   public static AllocationTechnique allocationTechnique;

   private DiskManager(){}
    public static ArrayList<Integer> getBlocks() {
        return blocks;
    }

    public static void setBlocks(ArrayList<Integer> blocks) {
        DiskManager.blocks = blocks;
    }

    public static void setAllocationTechnique(AllocationTechnique allocationTechnique) {
        DiskManager.allocationTechnique = allocationTechnique;
    }

    public static AllocationTechnique getAllocationTechnique() {
        return allocationTechnique;
    }


    public static boolean allocate(File file, int size){
       return allocationTechnique.allocate(file,size);
    }
    public static boolean deAllocate(Directory root, ArrayList<String> path){

        if(path.size()==2){
            for (int i = 0;i<root.getFiles().size();i++){
                if(!root.getFiles().get(i).isDeleted()){
                    if (root.getFiles().get(i).getFileName().equals(path.get(1))){
                        for(int j = 0;j<root.getFiles().get(i).getAllocatedBlocks().size();j++){
                            DiskManager.blocks.set(root.getFiles().get(i).getAllocatedBlocks().get(j),0);

                        }
                        root.getFiles().get(i).setDeleted(true);
                        root.getFiles().get(i).getAllocatedBlocks().clear();

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
                    return deAllocate(root.getSubDirectories().get(i),path);
                }

            }
        }

        return false;

    }
    public static ArrayList<Integer> getFreeBlocks(){
       ArrayList<Integer> result = new ArrayList<>();
        for (int block=0;block<blocks.size();block++) {
            if(blocks.get(block)==0)
                result.add(block);
        }
        return result;
    }
    public static ArrayList<Integer> getAllocatedBlocks(){
        ArrayList<Integer> result = new ArrayList<>();
        for (int block=0;block<blocks.size();block++) {
            if(blocks.get(block)==1)
                result.add(block);
        }
        return result;
    }

}
