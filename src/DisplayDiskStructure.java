public class DisplayDiskStructure extends Command{
    public DisplayDiskStructure(Directory root, String[] splitedCommand) {
        super(root, splitedCommand);
    }

    @Override
    void execute() {
        root.printDirectoryStructure(0);
    }
}
