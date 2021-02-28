package Commands;

import Other.Person;

import java.util.Iterator;
import java.util.LinkedList;

public class RemoveByPass extends Command{

    public RemoveByPass(CommandHandler ch){
        super(ch);
    }

    private LinkedList<Person> people;

    @Override
    public boolean execute(String... args){
        if (args!=null) {
            if (args.length!=1) {
                System.out.println("Вы должны ввести только один аргумент - ID паспорта.");
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
                if (iter.next().getPassportAsLong().equals(id)) {
                    iter.remove();
                    result = true;
                }
            }
            if (!result) System.out.println("Элементов с таким PassportID не было");
            else System.out.println("Объекты с PassportID "+id+" успешно удалены");
            return true;
        }
        else {
            System.out.println("Вы не ввели аргумент - ID паспорта");
            return false;
        }
    }

    @Override
    public String getName() {
        return "remove_all_by_passport_id";
    }

    @Override
    public String getDescription() {
        return "remove_all_by_passport_id passportID : удалить из коллекции все элементы, значение поля passportID которого эквивалентно заданному";
    }
}
