package Commands;

import Other.Person;

import java.io.IOException;
import java.util.LinkedList;

public class Clear extends Command{

    public Clear(CommandHandler ch){
        super(ch);
    }

    private LinkedList<Person> people;

    @Override
    public boolean execute(String... args) throws IOException {
        if (args==null){
            people=ch.getPeople();
            people.clear();
            System.out.println("Коллекция успешно очищена.");
            return true;
        }
        else {
            System.out.println("У команды clear нет аргументов. Повторите ввод.");
            return false;
        }
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
}
