package Commands;

import Other.CommandHandler;
import Other.Person;

import java.util.LinkedList;

public class CountLessPass extends Command{

    public CountLessPass(CommandHandler ch){
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
            try{
                id=Long.parseLong(args[0]);
                if (id<0) {
                    System.out.println("ID паспорта не может быть отрицательным числом");
                    return false;
                }
            } catch (Exception e){
                System.out.println("В качестве аргумента передано не число");
                return false;
            }
            people=ch.getPeople();
            int res=0;
            for (Person p:people){
                if (p.getPassportAsLong()<id) res++;
            }
            System.out.println(res+" - число элементов, значение поля passportID которых меньше "+id);
            return true;
        }
        else {
            System.out.println("Вы не ввели аргумент - ID паспорта");
            return false;
        }
    }

    @Override
    public String getName() {
        return "count_less_than_passport_id";
    }

    @Override
    public String getDescription() {
        return "count_less_than_passport_id passportID : вывести количество элементов, значение поля passportID которых меньше заданного";
    }
}
