public abstract class Command {
    Directory root;
    String[] splitedPath;
    String[] splitedCommand;
    
    User user;

    public Command(String userName,String passWord) {
        this.user = new User(userName,passWord);
    }

    abstract void execute();

    public Command(Directory root, String[] splitedPath, String[] splitedCommand) {
        this.root = root;
        this.splitedPath = splitedPath;
        this.splitedCommand = splitedCommand;
    }

    public Command(Directory root, String[] splitedCommand) {
        this.root = root;
        this.splitedCommand = splitedCommand;
    }
}
