package commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import other.CommandHandler;
import other.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Команда сохраняет коллекцию в заданный файл
 */
public class Save extends Command {

    /**
     * Конструктор - создание нового объекта
     *
     * @param ch - обработчик команд
     */
    public Save(CommandHandler ch) {
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
            String dir = System.getenv("output");
            try (PrintWriter pw = new PrintWriter(new File(dir))) {
                LinkedList<Person> people = ch.getPeople();
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                String jsonString;
                for (Person p : people) {
                    jsonString = gson.toJson(p);
                    pw.write(jsonString + "\n");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Файл для записи коллекции не найден. Убедитесь, что вы правильно указали название файла и введите команду снова.");
            }
            return true;
        } else {
            System.out.println("У команды save нет аргументов. Введите команду снова. ");
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
        return "save";
    }

    /**
     * Возвращает описание команды
     *
     * @return описание
     */
    @Override
    public String getDescription() {
        return "save : сохранить коллекцию в файл";
    }
}
