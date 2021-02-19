package Commands;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    private Map<String, Command> commands =  new HashMap<String, Command>();

    public CommandHandler(){
        Command c = new Help(); //help
        commands.put(c.getName(), c);
        c = new Exit(); //exit
        commands.put(c.getName(), c);
    }

    private void run(){
        boolean isFound = false;
        System.out.print(">");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        for (Command c: commands.values()) {
            if (input.equalsIgnoreCase(c.getName())) {
                c.run();
                isFound = true;
            }
        }
        if (!isFound) System.out.println("Пожалуйста, повторите ввод");
        run();
    }

    public void setStart(){
        run();
    }

    public Map getMap(){
        return  commands;
    }

}

