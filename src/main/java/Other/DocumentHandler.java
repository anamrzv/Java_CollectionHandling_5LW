package Other;

import java.io.*;
import java.util.*;
import com.google.gson.*;

/** Обработчик файла, которым заполняется коллекция при запуске */
public class DocumentHandler {

    /** Поле - обработчик командз */
    private CommandHandler ch;

    /** Поле - связный список объектов Person */
    private LinkedList<Person> people;

    /** Поле - отображение объектов Location */
    private Map<Integer,Location> readyLocations;

    /** Поле - строка файла */
    private String jsonLine;

    /** Конструктор - создание нового объекта
     * @param ch - обработчик команд
     */
    public DocumentHandler(CommandHandler ch){
        people=ch.getPeople();
        readyLocations= ch.getLocations();
        this.ch=ch;
    }

    /** Главный метод класса, запускает обработчик файла*/
    private void read(){
        String homeDir = System.getenv("start5");
        File file = new File(homeDir);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        int num=0;
            try(BufferedReader br = new BufferedReader(new FileReader(homeDir))) {
                jsonLine = br.readLine();
                while (jsonLine != null) {
                    num+=1;
                    Person pers = gson.fromJson(jsonLine, Person.class);
                    if(pers.getName()==null||pers.getPassportID()==null||pers.getHairColor()==null||pers.getLocation()==null||pers.getCoordinates()==null){
                        printErrorMsg(num, jsonLine, file);
                        System.out.println("Проверьте, что заполнены все обязательны поля: name, passport id, hair color, location, coordinates, а в поле hair color правильно указан цвет.");
                        System.exit(0);
                    }
                    else if (!ch.validateName(pers.getName())) {
                        printErrorMsg(num, jsonLine, file);
                        System.out.println("В имени не могут содержаться цифры и спец. знаки");
                        System.exit(0);
                    }
                    else if (!ch.validatePassport(pers.getPassportID())){
                        printErrorMsg(num, jsonLine, file);
                        System.out.println("В passport id должны содержаться только цифры.");
                        System.exit(0);
                    }
                    else if (pers.getPassportID().length()<10 || pers.getPassportID().length()>27){
                        printErrorMsg(num, jsonLine, file);
                        System.out.println("Passport id должен содержать от 10 до 27 цифр, проверьте длину.");
                        System.exit(0);
                    }
                    pers.setTime();
                    pers.setID();
                    people.add(pers);
                    Location currentLocation = pers.getLocation();
                    boolean alreadyLocation=false;
                    for (Location l: readyLocations.values()){
                        if (currentLocation.equals(l)) {
                            alreadyLocation=true;
                            break;
                        }
                    }
                    if (!alreadyLocation) readyLocations.put(readyLocations.size()+1,pers.getLocation());
                    jsonLine = br.readLine();
                }
            } catch (JsonSyntaxException e) {
                printErrorMsg(num,jsonLine,file);
                System.out.println("Проверьте, что там, где строки, не записаны числа, и наоборот.");
                System.exit(0);
            }
            catch (Exception e){
                System.out.println("Не удается найти файл с таким названием."+e);
            }
    }

    /** Метод - вывести отформатированное сообщение об ошибке в файле */
    private void printErrorMsg(int num, String jsonLine, File file){
        System.out.println("Ошибка в данных исходного файла "+file+", перезапишите файл и запустите программу снова.");
        System.out.println("Ошибка в следующей строке => "+num);
        System.out.println("Неправильная строка => "+ jsonLine);
    }

    /** Метод - запустить read */
    public void setRead(){
        read();
    }

}
