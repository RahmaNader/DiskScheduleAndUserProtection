import java.util.ArrayList;

public class ContiguousAllocation implements AllocationTechnique{
    int startBlock;
    int numberOfBlocks;


    public boolean allocate(File file,int filesize){
        int freeBlocks = 0;
        for (int i = 0; i < DiskManager.getBlocks().size(); i++)
            if (DiskManager.getBlocks().get(i) == 0)
                freeBlocks++;

        if(filesize > freeBlocks)
            return false;

        //you can add a parameter with path to map the path to the list of allocated blocks if it would help in writing in file
        int startIndex = -1,currentBestFit = Integer.MAX_VALUE, cnt = 0;
        boolean emptyToTheEnd = false;
        for(int i = 0; i< DiskManager.blocks.size(); i++){
            if(DiskManager.blocks.get(i).equals(0)){
                cnt++;
                emptyToTheEnd = true;

            }
            else{
                emptyToTheEnd = false;
                if(cnt>=filesize){
                    if(cnt<currentBestFit){
                        currentBestFit = cnt;
                        startIndex = i-cnt;
                    }
                }
                cnt = 0;
            }
        }
        if(emptyToTheEnd){
            if(cnt>=filesize){
                if(cnt<currentBestFit) startIndex = DiskManager.blocks.size()-cnt;
            }
        }
        //System.out.println("cnt: "+cnt);
        //System.out.println("startIndex"+startIndex);
        if(startIndex !=-1){
            startBlock = startIndex;
            numberOfBlocks = filesize;
            for(int i = 0;i<filesize;i++){
                file.getAllocatedBlocks().add(startIndex);
                DiskManager.blocks.set(startIndex++,1);
            }
            System.out.println("File is created Successfully");
            return true;
        }
        System.out.println("failed to occupy due to insufficient number of contiguous blocks");
        return false;
    }


    public int getStartBlock() {
        return startBlock;
    }

    public int getNumberOfBlocks() {
        return numberOfBlocks;
    }
    @Override
    public String toString() {
        return "Contiguous";
    }
}