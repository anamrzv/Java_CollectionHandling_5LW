package Commands;

import Other.Person;

import java.util.LinkedList;

public class Head extends Command{

    public Head(CommandHandler ch){
        super(ch);
    }

    LinkedList<Person> people;

    @Override
    public boolean execute(String... args){
        if (args==null){
            people=ch.getPeople();
            System.out.println("Первый элемент отсортированной коллекции: ");
            System.out.println(people.get(0));
            return true;
        }
        else {
            System.out.println("У команы head нет аргументов. Повторите ввод.");
            return false;
        }
    }

    @Override
    public String getName() {
        return "head";
    }

    @Override
    public String getDescription() {
        return "head : вывести первый элемент коллекции";
    }
}
