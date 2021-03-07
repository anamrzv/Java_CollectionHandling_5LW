package Commands;

import java.io.*;
import java.util.*;
import com.google.gson.*;
import Other.*;

public class Save extends Command{

    private LinkedList<Person> people;

    public Save(CommandHandler ch){
        super(ch);
    }

    @Override
    public boolean execute(String... args){
        if (args == null) {
            try (PrintWriter pw = new PrintWriter(new File("C:\\Users\\Ana\\Programming\\Laba_5\\src\\main\\resources\\output.txt"))) {
                people = ch.getPeople();
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                String jsonString;
                for (Person p : people) {
                    jsonString = gson.toJson(p);
                    pw.write(jsonString + "\n");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Файл для записи не найден.");
            }
            return true;
        }
        else {
            System.out.println("У команды save нет аргументов");
            return false;
        }

    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "save : сохранить коллекцию в файл";
    }
}
