package Other;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;
import com.google.gson.*;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;


public class DocumentHandler {
//данные в файле хранятся в json и я должна прочитать их и создать объектики
//один объект = одна строка
//считали строку
//парсер - создали объект
    private CommandHandler ch;
    private LinkedList<Person> people;

    public DocumentHandler(CommandHandler ch){
        people=ch.getPeople();
        this.ch=ch;
    }

// name height weight passportid haircolor locX locY locZ locName coorX coorY
    private void read() throws IOException {


        //String homeDir = System.getenv("lab5.json");
        //String file = homeDir; // чтобы открывалось с любого компьютера
        File file = new File("C:\\Users\\Ana\\Programming\\Laba_5\\src\\main\\resources\\laba5.txt");

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String jsonLine = br.readLine();
        int num=0;

            try {
                while (jsonLine != null) {
                    num+=1;
                    System.out.println(jsonLine);
                    Person pers = gson.fromJson(jsonLine, Person.class);
                    if(pers.getName()==null||pers.getPassportID()==null||pers.getHairColor()==null||pers.getLocation()==null||pers.getCoordinates()==null){
                        printErrorMsg(num, jsonLine, file);
                        System.out.println("Проверьте, что заполнены все обязательны поля: name, passport id, hair color, location, coordinates.");
                        System.exit(0);
                    }
                    else if (!ch.validateName(pers.getName())) {
                        printErrorMsg(num, jsonLine, file);
                        System.out.println("В имени не могут содержаться цифры и спец. знаки");
                        System.exit(0);
                    }
                    pers.setTime();
                    pers.setID();
                    people.add(pers);
                    jsonLine = br.readLine();
                }
                fr.close();
                br.close();
            } catch (JsonSyntaxException e) {
                System.out.println("Ошибка в данных исходного файла "+file+", перезапишите файл и запустите программу снова.");
                System.out.println("Ошибка в следующей строке => "+num);
                System.out.println("Неправильная строка => "+ jsonLine);
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
