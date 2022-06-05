public class DisplayDiskStatus extends Command{

    public DisplayDiskStatus(Directory root, String[] splitedCommand) {
        super(root, splitedCommand);
    }

    @Override
    void execute() {
        System.out.println(DiskManager.getFreeBlocks().size()+" KB");
        System.out.println(DiskManager.getAllocatedBlocks().size()+" KB");
        System.out.println(DiskManager.getFreeBlocks());
        System.out.println(DiskManager.getAllocatedBlocks());
    }
}
