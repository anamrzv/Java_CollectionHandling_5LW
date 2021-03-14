package other;

import commands.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Обработчик команд - все действия с коллекцией
 */
public class CommandHandler {

    /**
     * Поле - отображение объектов Command
     */
    private final Map<String, Command> commands = new HashMap<>();

    /**
     * Поле - отображение объектов Location
     */
    private final Map<Integer, Location> readyLocations = new HashMap<>();

    /**
     * Поле - связный список объектов Person
     */
    private final LinkedList<Person> people = new LinkedList<>();

    /**
     * Поле - последний добавленный в коллекцию объект
     */
    private int lastPersonNum;

    {
        Command c = new Help(this);
        commands.put(c.getName(), c);
        c = new Exit(this);
        commands.put(c.getName(), c);
        c = new SimpleAdd(this);
        commands.put(c.getName(), c);
        c = new Info(this);
        commands.put(c.getName(), c);
        c = new Show(this);
        commands.put(c.getName(), c);
        c = new RemoveByID(this);
        commands.put(c.getName(), c);
        c = new Clear(this);
        commands.put(c.getName(), c);
        c = new Head(this);
        commands.put(c.getName(), c);
        c = new SumOfWeight(this);
        commands.put(c.getName(), c);
        c = new RemoveByPass(this);
        commands.put(c.getName(), c);
        c = new CountLessPass(this);
        commands.put(c.getName(), c);
        c = new AddIfMax(this);
        commands.put(c.getName(), c);
        c = new AddIfMin(this);
        commands.put(c.getName(), c);
        c = new Update(this);
        commands.put(c.getName(), c);
        c = new Save(this);
        commands.put(c.getName(), c);
        c = new ExecuteScript(this);
        commands.put(c.getName(), c);
    }

    /**
     * Конструктор - создание нового объекта, автоматическое заполнение коллекции из файла
     */
    public CommandHandler() {
        DocumentHandler doc = new DocumentHandler(this);
        try {
            doc.setRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Главный метод класса, запускает обработчик команд
     */
    private void run() {
        do {
            try {
                boolean isFound = false;
                System.out.print(">");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input = br.readLine().trim();

                String cmd = parseCommand(input);
                String[] args = getArguments(input);
                for (Command c : commands.values()) {
                    if (cmd.equalsIgnoreCase(c.getName())) {
                        isFound = true;
                        executeCommand(c, args);
                        break;
                    }
                }
                if (!isFound) System.out.println("Пожалуйста, повторите ввод: команда не распознана");
            } catch (Exception e) {
                System.out.println("Неверный формат ввода команды. Введите команду еще раз.");
            }
        } while (true);
    }

    /**
     * Метод - запускает команду
     */
    private void executeCommand(Command c, String[] args) throws IOException {
        c.execute(args);
    }

    /**
     * Метод - обновление поля последнего добавленного объекта коллекции
     */
    public void addLastPersonNum(int number) {
        lastPersonNum = number;
    }

    /**
     * Метод - геттер индекса последнего добавленного объекта коллекции
     *
     * @return int индекс
     */
    public int getLastPersonNum() {
        return lastPersonNum;
    }

    /**
     * Метод - геттер коллекции людей
     *
     * @return LinkedList<Person> коллекция
     */
    public LinkedList<Person> getPeople() {
        return people;
    }

    /**
     * Метод- добавляет локацию в коллекцию
     */
    public void addLocation(Location l) {
        readyLocations.put(readyLocations.size() + 1, l);
    }

    /**
     * Метод - геттер коллекции локаций
     *
     * @return Map<Integer, Location> коллекция
     */
    public Map<Integer, Location> getLocations() {
        return readyLocations;
    }

    /**
     * Метод - возвращает имя команды
     *
     * @param input - строка
     * @return String - имя команды
     */
    public String parseCommand(String input) {
        String[] elements = input.split(" +");
        return elements[0]; //только название команды
    }

    /**
     * Метод - возвращает аргументы команды
     *
     * @param input - строка
     * @return String[] - аргументы команды
     */
    public String[] getArguments(String input) {
        String[] args;
        String[] elements = input.split(" +");
        if (elements.length > 1) {
            args = new String[elements.length - 1];
            System.arraycopy(elements, 1, args, 0, args.length);
            return args;
        } else return null;
    }

    /**
     * Метод - запускает run
     */
    public void setStart() {
        run();
    }

    /**
     * Метод - геттер коллекции команд
     *
     * @return Map<String, Command> коллекция
     */
    public Map<String, Command> getMap() {
        return commands;
    }

    /**
     * Метод - валидация на отсутствие цифр и спец символов в строке
     *
     * @return boolean true/false
     */
    public boolean validateName(String name) {
        Pattern pattern = Pattern.compile("^[\\p{L} ]+$");
        Matcher m = pattern.matcher(name);
        return m.matches();
    }

    /**
     * Метод - валидация на отсутствие букв в строке
     *
     * @return boolean true/false
     */
    public boolean validatePassport(String pass) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher m = pattern.matcher(pass);
        return m.matches();
    }

}

