import java.util.ArrayList;

public class Linked implements AllocationTechnique {

    @Override
    public boolean allocate(File file,int size)
    {
        ArrayList<Integer> list = new ArrayList<>();
        int freeBlocks = 0;
        for (int i = 0; i < DiskManager.getBlocks().size(); i++) {
            if (DiskManager.getBlocks().get(i) == 0)
                freeBlocks++;
        }
        if(size > freeBlocks)
            return false;

        else{
            boolean start=false;
            for (int i = 0; i< DiskManager.getBlocks().size() ; i++)
            {
                if(DiskManager.getBlocks().get(i) ==0)
                {
                    if(!start)
                        start=true;

                    list.add(i);
                    file.getAllocatedBlocks().add(i);
                    DiskManager.getBlocks().set(i, 1);
                }
                if (list.size() == size)
                    break;
            }
            file.setAllocatedBlocks(list);
            System.out.println("linked file is created successfully.");
            return true;
        }
    }
    @Override
    public String toString() {
        return "Linked";
    }

}