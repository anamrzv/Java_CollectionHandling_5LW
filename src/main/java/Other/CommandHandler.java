package Other;

import Commands.*;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandHandler {

    private Map<String, Command> commands =  new HashMap<>();
    private Map<Integer, Location> readyLocations = new HashMap<>();
    private LinkedList<Person> people = new LinkedList<>();
    private int lastPersonNum;

    {
        Command c = new Help(this);
        commands.put(c.getName(), c);
        c = new Exit(this);
        commands.put(c.getName(), c);
        c = new SimpleAdd(this);
        commands.put(c.getName(), c);
        c = new Info(this);
        commands.put(c.getName(), c);
        c = new Show(this);
        commands.put(c.getName(), c);
        c = new RemoveByID(this);
        commands.put(c.getName(),c);
        c = new Clear(this);
        commands.put(c.getName(), c);
        c = new Head(this);
        commands.put(c.getName(),c);
        c = new SumOfWeight(this);
        commands.put(c.getName(),c);
        c = new RemoveByPass(this);
        commands.put(c.getName(), c);
        c = new CountLessPass(this);
        commands.put(c.getName(),c);
        c = new AddIfMax(this);
        commands.put(c.getName(), c);
        c = new AddIfMin(this);
        commands.put(c.getName(),c);
        c = new Update(this);
        commands.put(c.getName(), c);
        c = new Save(this);
        commands.put(c.getName(),c);
        c = new ExecuteScript(this);
        commands.put(c.getName(), c);
    }

    public CommandHandler() {
        DocumentHandler doc = new DocumentHandler(this);
        try {
            doc.setRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run() {
        do {
            try {
                boolean isFound = false;
                System.out.print(">");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input = br.readLine().trim();

                String cmd = parseCommand(input);
                String[] args = getArguments(input);
                for (Command c : commands.values()) {
                    if (cmd.equalsIgnoreCase(c.getName())) {
                        isFound = true;
                        executeCommand(c, args);
                    }
                }
                if (!isFound) System.out.println("Пожалуйста, повторите ввод: команда не распознана");
                run();
            } catch (Exception e) {
                System.out.println("Неверный формат ввода команды. Введите команду еще раз.");
            }
        }while (true);
    }

    private void executeCommand(Command c, String[] args) throws IOException {
        c.execute(args);
    }

    public void addLastPersonNum(int numer){
        lastPersonNum=numer;
    }

    public int getLastPersonNum(){
        return lastPersonNum;
    }

    public LinkedList<Person> getPeople(){
        return people;
    }

    public void addLocation(Location l){
        readyLocations.put(readyLocations.size()+1,l);
    }

    public Map<Integer, Location> getLocations(){
        return readyLocations;
    }

    public String parseCommand(String input){
        String[] elements = input.split(" ");
        return elements[0]; //только название команды
    }

    public String[] getArguments(String input){
        String[] args;
        String[] elements = input.split(" ");
        if (elements.length>1){
             args = new String[elements.length-1];
             System.arraycopy(elements, 1, args, 0, args.length);
             return args;
        }
        else return null;
    }

    public void setStart() {
        run();
    }

    public Map<String, Command> getMap(){
        return  commands;
    }

    public boolean validateName(String name){
        Pattern pattern = Pattern.compile("^[a-zA-Zа-яА-Я ]+$");
        Matcher m = pattern.matcher(name);
        boolean hasNoDigit = m.matches();
        return hasNoDigit;
    }

    public boolean validatePassport(String pass){
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher m = pattern.matcher(pass);
        boolean hasNoLetter = m.matches();
        return hasNoLetter;
    }




}

