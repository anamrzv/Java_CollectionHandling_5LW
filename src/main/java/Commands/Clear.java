package Commands;

import Other.CommandHandler;
import Other.Person;

import java.io.IOException;
import java.util.LinkedList;

/** Команда очищает коллекцию */
public class Clear extends Command{

    /** Поле - связный список объектов Person */
    private LinkedList<Person> people;

    /** Конструктор - создание нового объекта
     * @param ch - обработчик команд
     */
    public Clear(CommandHandler ch){
        super(ch);
    }

    /** Главный метод класса, запускает команду
     * @param args Параметры командной строки
     * @return true/false Успешно ли завершилась команда
     */
    @Override
    public boolean execute(String... args) throws IOException {
        if (args==null){
            people=ch.getPeople();
            people.clear();
            System.out.println("Коллекция успешно очищена.");
            return true;
        }
        else {
            System.out.println("У команды clear нет аргументов. Введите команду снова.");
            return false;
        }
    }

    /** Возвращает имя команды
     * @return имя
     */
    @Override
    public String getName() {
        return "clear";
    }

    /** Возвращает описание команды
     * @return описание
     */
    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
}
