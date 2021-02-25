package Commands;

import java.util.*;
import Other.*;

public class Info implements Command{

    @Override
    public void run(CommandHandler ch) {
        LinkedList<Person> people = ch.getPeople();
        Collections.sort(people);
        System.out.println("Тип коллекции: "+people.getClass());
        System.out.println("Тип элементов: Person");
        System.out.println("Количество элементов: "+ people.size());
        System.out.println("Дата инициализации: "+ people.get(0).getTime());
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
