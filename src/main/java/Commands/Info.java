package Commands;

import java.util.*;
import Other.*;

public class Info extends Command{

    public Info(CommandHandler ch){
        super(ch);
    }

    @Override
    public boolean execute(String...args) {
        if (args == null) {
            LinkedList<Person> people = ch.getPeople();
            Collections.sort(people);
            System.out.println("Тип коллекции: " + people.getClass());
            System.out.println("Тип элементов: Person");
            System.out.println("Количество элементов: " + people.size());
            System.out.println("Дата инициализации: " + people.get(0).getTime());
            return true;
        }
        else {
            System.out.println("У команды info нет аргументов. Повторите ввод.");
            return false;
        }
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
