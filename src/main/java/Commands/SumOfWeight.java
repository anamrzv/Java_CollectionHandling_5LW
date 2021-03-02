package Commands;

import Other.CommandHandler;
import Other.Person;

import java.util.LinkedList;

public class SumOfWeight extends Command{

    public SumOfWeight(CommandHandler ch){
        super(ch);
    }

    private LinkedList<Person> people;

    @Override
    public boolean execute(String... args) {
        if (args==null){
            people=ch.getPeople();
            long sum=0;
            for (Person p:people){
                sum+=p.getWeight();
            }
            System.out.println(sum+" - сумма значений поля weight всех элементов коллекции");
            return true;
        }
        else{
            System.out.println("У команды sum_of_weight нет аргументов. Повторите ввод.");
            return false;
        }
    }

    @Override
    public String getName() {
        return "sum_of_weight";
    }

    @Override
    public String getDescription() {
        return "sum_of_weight : вывести сумму значений поля weight для всех элементов коллекции";
    }
}
