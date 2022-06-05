import java.util.ArrayList;
import java.util.HashMap;

public class Indexed implements AllocationTechnique {
    HashMap<Integer, ArrayList<Integer>> indexToList;
    HashMap<String, Integer> pathToIndex;

    @Override
    public boolean allocate(File file,int size)
    {
        int freeBlocks = 0;
        for (int i = 0; i < DiskManager.getBlocks().size(); i++) {
            if (DiskManager.getBlocks().get(i) == 0)
                freeBlocks++;
        }
        if(size > freeBlocks)
            return false;
        else {
            ArrayList<Integer> list = new ArrayList<>();
            int index = -1;
            for (int i = 0; i < DiskManager.getBlocks().size(); i++) {
                if (DiskManager.getBlocks().get(i) == 0) {
                    if (index == -1)
                        index = i;
                    else
                        list.add(i);
                    DiskManager.getBlocks().set(i,1);
                }
                if (list.size() == size)
                    break;
            }
            file.setAllocatedBlocks(list);
            list.add(0,index);
            return true;
        }

    }

    @Override
    public String toString() {
        return "Indexed";
    }
}