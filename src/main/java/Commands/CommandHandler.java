package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        c = new SimpleAdd(); //simple add
        commands.put(c.getName(), c);
    }

    private void run() throws IOException {
        boolean isFound = false;
        System.out.print(">");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().trim();

        input = parseCommand(input);
        for (Command c: commands.values()) {
            if (input.equalsIgnoreCase(c.getName())) {
                c.run();
                isFound = true;
            }
        }
        if (!isFound) System.out.println("Пожалуйста, повторите ввод");
        run();
    }

    private String parseCommand(String input){
        String[] elements = input.split(" ");
        return elements[0];
    }

    public void setStart() throws IOException {
        run();
    }

    public Map getMap(){
        return  commands;
    }

}

