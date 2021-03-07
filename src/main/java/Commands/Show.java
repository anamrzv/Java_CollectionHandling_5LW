package Commands;

import Other.CommandHandler;
import Other.Person;
import java.util.Collections;
import java.util.LinkedList;

public class Show extends Command {

    public Show(CommandHandler ch){
        super(ch);
    }

    @Override
    public boolean execute(String... args){
        if (args==null) {
            LinkedList<Person> people = ch.getPeople();
            if (people.size() == 0) System.out.println("Коллекция People пуста.");
            else {
                Collections.sort(people);
                System.out.println("Коллекция People:");
                for (Person p : people) {
                    System.out.println(p);
                }
            }
            return true;
        }
        else {
            System.out.println("У команды show нет аргументов. Повторите ввод.");
            return false;
        }
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
