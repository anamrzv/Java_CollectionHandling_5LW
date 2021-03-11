package commands;

import other.CommandHandler;
import other.Person;

import java.util.LinkedList;

/**
 * Команда выводит первый элемент коллекции
 */
public class Head extends Command {

    /**
     * Поле - связный список объектов Person
     */
    LinkedList<Person> people;

    /**
     * Конструктор - создание нового объекта
     *
     * @param ch - обработчик команд
     */
    public Head(CommandHandler ch) {
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
        if (args == null) {
            people = ch.getPeople();
            System.out.println("Первый элемент отсортированной коллекции: ");
            System.out.println(people.get(0));
            return true;
        } else {
            System.out.println("У команы head нет аргументов. Введите команду снова.");
            return false;
        }
    }

    /**
     * Возвращает имя команды
     *
     * @return имя
     */
    @Override
    public String getName() {
        return "head";
    }

    /**
     * Возвращает описание команды
     *
     * @return описание
     */
    @Override
    public String getDescription() {
        return "head : вывести первый элемент коллекции";
    }
}
