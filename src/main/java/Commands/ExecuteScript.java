package Commands;

import Other.*;

import java.io.*;
import java.util.*;

public class ExecuteScript extends Command {

    public ExecuteScript(CommandHandler ch){
        super(ch);
    }

    @Override
    public boolean execute(String... args){
            if (args.length == 1) {
                File file = new File(args[0]);
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    Map<String, Command> commands = ch.getMap();
                    String line = br.readLine().trim();
                    while (line != null) {
                        if (line.equals("execute_script "+args[0])){
                            System.out.println("Скрипт пытается вывести сам себя, удалите строку execute_script "+args[0]);
                            return false;
                        }
                        else {
                            String cmd = ch.parseCommand(line);
                            String[] arguments = ch.getArguments(line);
                            Command command = commands.get(cmd);
                            command.execute(arguments);
                            System.out.println("");
                            line = br.readLine().trim();
                        }
                    }
                    return true;
                }catch (FileNotFoundException e) {
                    System.out.println("Файл с таким названием не найден");
                    return false;
                }catch (Exception e) {
                    System.out.println(e);
                    return false;
                }
            }

            else {
                System.out.println("В качестве арумента должно быть передано имя файла");
                return false;
            }
        }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором они вводятся в интерактивном режиме.";
    }
}
