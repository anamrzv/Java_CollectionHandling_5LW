package other;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Map;

/**
 * Обработчик файла, которым заполняется коллекция при запуске
 */
public class DocumentHandler {

    /**
     * Поле - обработчик командз
     */
    private final CommandHandler ch;

    /**
     * Поле - связный список объектов Person
     */
    private final LinkedList<Person> people;

    /**
     * Поле - отображение объектов Location
     */
    private final Map<Integer, Location> readyLocations;

    /**
     * Поле - строка файла
     */
    private String jsonLine;

    /**
     * Конструктор - создание нового объекта
     *
     * @param ch - обработчик команд
     */
    public DocumentHandler(CommandHandler ch) {
        people = ch.getPeople();
        readyLocations = ch.getLocations();
        this.ch = ch;
    }

    /**
     * Главный метод класса, запускает обработчик файла
     */
    private void read() {
        String homeDir = System.getenv("start5");
        Path path = Paths.get(homeDir);
        File file = new File(homeDir);


        int num = 0;
        if (Files.exists(path) && !Files.isRegularFile(path)) {
            System.out.println("Нельзя передать специальный файл для чтения коллекции. Введите команду снова с новым аргументом.");
        } else {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            try (BufferedReader br = new BufferedReader(new FileReader(homeDir))) {
                jsonLine = br.readLine();
                while (jsonLine != null) {
                    num += 1;
                    Person pers = gson.fromJson(jsonLine, Person.class);
                    if (pers.getName() == null || pers.getPassportID() == null || pers.getHairColor() == null || pers.getLocation() == null || pers.getCoordinates() == null) {
                        printErrorMsg(num, jsonLine, file);
                        System.out.println("Проверьте, что заполнены все обязательны поля: name, passport id, hair color, location, coordinates, а в поле hair color правильно указан цвет.");
                        System.exit(0);
                    } else if (!ch.validateName(pers.getName())) {
                        printErrorMsg(num, jsonLine, file);
                        System.out.println("В имени не могут содержаться цифры и спец. знаки");
                        System.exit(0);
                    } else if (!ch.validatePassport(pers.getPassportID())) {
                        printErrorMsg(num, jsonLine, file);
                        System.out.println("В passport id должны содержаться только цифры.");
                        System.exit(0);
                    } else if (pers.getPassportID().length() < 10 || pers.getPassportID().length() > 27) {
                        printErrorMsg(num, jsonLine, file);
                        System.out.println("Passport id должен содержать от 10 до 27 цифр, проверьте длину.");
                        System.exit(0);
                    }
                    pers.setTime();
                    pers.setID();
                    people.add(pers);
                    Location currentLocation = pers.getLocation();
                    boolean alreadyLocation = false;
                    for (Location l : readyLocations.values()) {
                        if (currentLocation.equals(l)) {
                            alreadyLocation = true;
                            break;
                        }
                    }
                    if (!alreadyLocation) readyLocations.put(readyLocations.size() + 1, pers.getLocation());
                    jsonLine = br.readLine();
                }
            } catch (JsonSyntaxException e) {
                printErrorMsg(num, jsonLine, file);
                System.out.println("В файле найдена ошибка. Проверьте, что там, где строки, не записаны числа, и наоборот. Обновите файл " + file.getName() + " и запустите программу снова");
                System.exit(0);
            } catch (Exception e) {
                System.out.println("Не удается найти файл для загрузки " + file.getName());
                System.exit(0);
            }
        }
    }

    /**
     * Метод - вывести отформатированное сообщение об ошибке в файле
     */
    private void printErrorMsg(int num, String jsonLine, File file) {
        System.out.println("Ошибка в данных исходного файла " + file + ", перезапишите файл и запустите программу снова.");
        System.out.println("Ошибка в следующей строке => " + num);
        System.out.println("Неправильная строка => " + jsonLine);
    }

    /**
     * Метод - запустить read
     */
    public void setRead() {
        read();
    }

}
