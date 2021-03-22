package commands;

import other.CommandHandler;
import other.Person;

import java.util.LinkedList;

/**
 * Команда добавляет новый элемент в коллекцию, если его id превышает значение id наибольшего элемента.
 */
public class AddIfMax extends Command {

    /**
     * Поле - команда add
     */
    private final SimpleAdd cmd = new SimpleAdd(ch);

    /**
     * Конструктор - создание нового объекта
     *
     * @param ch - обработчик команд
     */
    public AddIfMax(CommandHandler ch) {
        super(ch);
    }

    /**
     * Главный метод класса, запускает команду
     *
     * @param args Параметры командной строки
     * @return true/false Успешно ли завершилась команда
     */
    @Override
    public boolean execute(String... args) {
        LinkedList<Person> people = ch.getPeople();
        if (args == null) {
            System.out.println("У команды add_if_max должен быть аргумент - слово 'Person' или строка формата json. Введите команду снова.");
            return false;
        } else if (args.length == 1) {
            if (!cmd.execute(args)) {
                return false;
            } else {
                if (people.size() != 0) {
                    Person maxPerson = people.getLast();
                    int maybe = ch.getLastPersonNum();
                    Person maybePerson = people.get(maybe);
                    if (!maxPerson.equals(maybePerson)) {
                        System.out.println("Объект не добавлен в коллекцию, т.к. его id меньше наибольшего имеющегося.");
                        people.remove(maybePerson);
                        return false;
                    } else
                        System.out.println("Объект добавлен в коллекцию, т.к. его id больше прежнего максимального.");
                } else System.out.println("Объект добавлен в коллекцию, т.к. коллекция была пуста.");
            }
        }
        return true;
    }


    /**
     * Возвращает имя команды
     *
     * @return имя
     */
    @Override
    public String getName() {
        return "add_if_max";
    }

    /**
     * Возвращает описание команды
     *
     * @return описание
     */
    @Override
    public String getDescription() {
        return "add_if_max {element} : добавить новый элемент в коллекцию, если его id превышает значение id наибольшего элемента этой коллекции";
    }
}
