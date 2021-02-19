package Commands;
import java.util.Map;

public class Help implements Command{
    public void run(){
        CommandHandler commandHandler = new CommandHandler();
        Map<String,Command> commands = commandHandler.getMap();
        System.out.println("Доступные команды:");
        for (Command c : commands.values()){
            System.out.println(c.getName()+c.getDescription());
        }
    }

    public String getName(){
        return "help";
    }

    public String getDescription(){
        return " : вывести справку по доступным командам";
    }
}
