package Commands;

import Other.CommandHandler;

import java.io.IOException;

public abstract class Command {
    public CommandHandler ch;

    Command(CommandHandler ch){
        this.ch=ch;
    }

    public abstract boolean execute(String... args) throws IOException;
    public abstract String getName();
    public abstract String getDescription();
}
