package Commands;

import java.io.IOException;

public interface Command {
    public void run(CommandHandler ch) throws IOException;
    public String getName();
    public String getDescription();
}
