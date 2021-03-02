package Commands;
import Other.CommandHandler;

import java.util.Map;

public class Help extends Command{
    public Help(CommandHandler ch){
        super(ch);
    }

    public boolean execute(String... args){
        if (args==null){
        Map<String,Command> commands = ch.getMap();
        System.out.println("Доступные команды:");
        for (Command c : commands.values()){
            System.out.println(c.getDescription());
        }
        return true;
        }
        else {
            System.out.println("У команды help нет аргументов. Повторите ввод.");
            return false;
        }
    }

    public String getName(){
        return "help";
    }

    public String getDescription(){
        return "help : вывести справку по доступным командам";
    }
}
