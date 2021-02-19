package Commands;

public interface Command {
    public void run();
    public String getName();
    public String getDescription();
}
