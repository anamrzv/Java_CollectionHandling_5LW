package Commands;

import Other.Person;

import java.util.Iterator;
import java.util.LinkedList;

public class RemoveByID extends Command{

    public RemoveByID(CommandHandler ch){
        super(ch);
    }

    private LinkedList<Person> people;

    @Override
    public boolean execute(String... args) {
        if (args!=null) {
            if (args.length!=1) {
                System.out.println("Вы должны ввести только один аргумент - ID персоны.");
                return false;
            }
            Long id;
            boolean result = false;
            try{
                id=Long.parseLong(args[0]);
                if (id<0) return false;
            } catch (Exception e){
                System.out.println("В качестве аргумента передано не число");
                return false;
            }
            people=ch.getPeople();
            Iterator<Person> iter = people.iterator();
            while (iter.hasNext()){
                if (iter.next().getID().equals(id)) {
                    iter.remove();
                    result = true;
                }
            }
            if (!result) System.out.println("Элемента с таким ID не было");
            else System.out.println("Объект с ID "+id+" успешно удален");
            return true;
        }
        else {
            System.out.println("Вы не ввели аргумент - ID персоны");
            return false;
        }
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
