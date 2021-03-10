package Commands;

import java.io.*;
import java.util.*;
import com.google.gson.*;
import Other.*;

/** Команда сохраняет коллекцию в заданный файл */
public class Save extends Command{

    /** Поле - связный список объектов Person */
    private LinkedList<Person> people;

    /** Конструктор - создание нового объекта
     * @param ch - обработчик команд
     */
    public Save(CommandHandler ch){
        super(ch);
    }

    /** Главный метод класса, запускает команду
     * @param args Параметры командной строки
     * @return true/false Успешно ли завершилась команда
     */
    @Override
    public boolean execute(String... args){
        if (args == null) {
            try (PrintWriter pw = new PrintWriter(new File("C:\\Users\\Ana\\Programming\\Laba_5\\src\\main\\resources\\output.txt"))) {
                people = ch.getPeople();
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
        }
        else {
            System.out.println("У команды save нет аргументов. Введите команду снова. ");
            return false;
        }

    }

    /** Возвращает имя команды
     * @return имя
     */
    @Override
    public String getName() {
        return "save";
    }

    /** Возвращает описание команды
     * @return описание
     */
    @Override
    public String getDescription() {
        return "save : сохранить коллекцию в файл";
    }
}
