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
        Command c = new Help(); //help
        commands.put(c.getName(), c);
        c = new Exit(); //exit
        commands.put(c.getName(), c);
        c = new SimpleAdd(); //simple add
        commands.put(c.getName(), c);
        c = new Info(); //info
        commands.put(c.getName(), c);
        c = new Show(); //show
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
                isFound = true;
                c.run(this);
            }
        }
        if (!isFound) System.out.println("Пожалуйста, повторите ввод");
        run();
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
        return elements[0];
    }

    public void setStart() throws IOException {
        run();
    }

    public Map getMap(){
        return  commands;
    }

}

