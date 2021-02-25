package Commands;

import Other.Person;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public class Show implements Command {

    @Override
    public void run(CommandHandler ch){
        LinkedList<Person> people = ch.getPeople();
        if (people.size()==0) System.out.println("Коллекция People пуста.");
        else {
            Collections.sort(people);
            System.out.println("Коллекция People:\n");
            for (Person p:people){
                System.out.println(p);
            }
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
