package Other;

import java.io.*;
import java.util.*;
import com.google.gson.*;


public class DocumentHandler {

    private CommandHandler ch;
    private LinkedList<Person> people;
    private Map<Integer,Location> readyLocations;
    private boolean alreadyLocation;
    private Location currentLocation;
    private String jsonLine;

    public DocumentHandler(CommandHandler ch){
        people=ch.getPeople();
        readyLocations= ch.getLocations();
        this.ch=ch;
    }

    private void read() throws IOException {
        //String homeDir = System.getenv("lab5.txt");
        //String file = homeDir; // чтобы открывалось с любого компьютера
        File file = new File("C:\\Users\\Ana\\Programming\\Laba_5\\src\\main\\resources\\laba5.txt");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();


        int num=0;
            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
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
                    currentLocation = pers.getLocation();
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
    }

    private void printErrorMsg(int num, String jsonLine, File file){
        System.out.println("Ошибка в данных исходного файла "+file+", перезапишите файл и запустите программу снова.");
        System.out.println("Ошибка в следующей строке => "+num);
        System.out.println("Неправильная строка => "+ jsonLine);
    }

    public void setRead() throws IOException {
        read();
    }

}
