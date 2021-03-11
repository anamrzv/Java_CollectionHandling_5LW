package commands;

import other.CommandHandler;
import other.Person;

import java.util.LinkedList;

/**
 * Команда очищает коллекцию
 */
public class Clear extends Command {

    /**
     * Конструктор - создание нового объекта
     *
     * @param ch - обработчик команд
     */
    public Clear(CommandHandler ch) {
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
            LinkedList<Person> people = ch.getPeople();
            people.clear();
            System.out.println("Коллекция успешно очищена.");
            return true;
        } else {
            System.out.println("У команды clear нет аргументов. Введите команду снова.");
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
        return "clear";
    }

    /**
     * Возвращает описание команды
     *
     * @return описание
     */
    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
}
