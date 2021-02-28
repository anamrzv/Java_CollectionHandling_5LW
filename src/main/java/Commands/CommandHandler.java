package Commands;

import Other.Location;
import Other.Person;

import java.io.*;
import java.util.*;

public class CommandHandler {

    private Map<String, Command> commands =  new HashMap<>();
    private Map<Integer, Location> readyLocations = new HashMap<>();
    private LinkedList<Person> people = new LinkedList<>();

    public CommandHandler(){
        Command c = new Help(this); //help
        commands.put(c.getName(), c);
        c = new Exit(this); //exit
        commands.put(c.getName(), c);
        c = new SimpleAdd(this); //simple add
        commands.put(c.getName(), c);
        c = new Info(this); //info
        commands.put(c.getName(), c);
        c = new Show(this); //show
        commands.put(c.getName(), c);
        c = new RemoveByID(this);
        commands.put(c.getName(),c); //remove by id
        c = new Clear(this);//clear
        commands.put(c.getName(), c);
        c = new Head(this);
        commands.put(c.getName(),c);
        c = new SumOfWeight(this);
        commands.put(c.getName(),c);
        c = new RemoveByPass(this);
        commands.put(c.getName(), c);
        c = new CountLessPass(this);
        commands.put(c.getName(),c);
    }

    private void run() throws IOException {
        boolean isFound = false;
        System.out.print(">");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().trim();

        String cmd = parseCommand(input);
        String[] args = getArguments(input);
        for (Command c: commands.values()) {
            if (cmd.equalsIgnoreCase(c.getName())) {
                isFound = true;
                executeCommand(c, args);
            }
        }
        if (!isFound) System.out.println("Пожалуйста, повторите ввод: команда не распознана");
        run();
    }

    private void executeCommand(Command c, String[] args) throws IOException {
        c.execute(args);
    }

    public void addPerson(Person p){
        people.add(p);
    }

    public LinkedList getPeople(){
        return people;
    }

    public void addLocation(Location l){
        readyLocations.put(readyLocations.size()+1,l);
    }

    public Map getLocations(){
        return readyLocations;
    }

    private String parseCommand(String input){
        String[] elements = input.split(" ");
        return elements[0]; //только название команды
    }

    private String[] getArguments(String input){
        String[] args;
        String[] elements = input.split(" ");
        if (elements.length>1){
             args = new String[elements.length-1];
             for (int i=0; i<args.length; i++){
                 args[i]=elements[i+1];
             }
             return args;
        }
        else return null;
    }

    public void setStart() throws IOException {
        run();
    }

    public Map getMap(){
        return  commands;
    }

}

