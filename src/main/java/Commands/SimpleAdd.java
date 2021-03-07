package Commands;

import Other.*;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.*;

public class SimpleAdd extends Command{

    public SimpleAdd(CommandHandler ch){
        super(ch);
    }

    private Map<Integer,Location> readyLocations;
    private Color hair;
    private LinkedList<Person> people;
    private Person p;
    private boolean alreadyLocation;

    public boolean execute(String... args) throws IOException {
        people=ch.getPeople();
        p = new Person();
        readyLocations = ch.getLocations();


        if (args==null) {
            System.out.println("У команды add должен быть аргумент - слово Person или строка формата json.");
            return false;
        }

        else if (args.length == 1 && args[0].equalsIgnoreCase("Person")) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
                System.out.println("Введите имя персоны (не пустая строка). Это обязательное поле");
                inputName(br, p);

                System.out.println("Введите рост. Он должен быть больше 0. Это необязательное поле. ");
                inputHeight(br, p);

                System.out.println("Введите вес. Он должен быть больше 0. Это необязательное поле");
                inputWeight(br, p);

                System.out.println("Введите ID паспорта. Длина ID должна лежать в диапазоне [10;27]. Это обязательное поле");
                inputPassport(br, p);

                System.out.println("Выберите цвет волос. Это обязательное поле.");
                inputHairColor(br, p);

                System.out.println("Выберите местоположение персоны из существующих. Это обязательное поле.");
                inputLocation(br, p, readyLocations);

                System.out.println("Введите координаты персоны. Это обязательное поле.");
                inputCoordinates(br, p);

                //System.out.println("Элемент " + p.getName()+" добавлен в коллекцию");
                p.setTime();
                p.setID();
                people.add(p);
                Collections.sort(people);
                int numer = people.indexOf(p);
                ch.addLastPersonNum(numer);

                return true;
            }catch (Exception e){
                System.out.println("Ошибка при чтении данных");
                return false;
            }
        }

        else if (args.length==1) {
            String regex = "\\{.+\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher m = pattern.matcher(args[0]);
            boolean isJson = m.matches();
            if (isJson) {
                System.out.println("Вы выбрали добавление элемента в формате json.");
                String jsonLine=args[0];
                try {
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    Person pers = gson.fromJson(jsonLine, Person.class);
                    if (pers.getName() == null || pers.getPassportID() == null || pers.getHairColor() == null || pers.getLocation() == null || pers.getCoordinates() == null) {
                        System.out.println("Проверьте, что заполнены все обязательны поля: name, passport id, hair color, location, coordinates, а в поле hair color правильно указан цвет.");
                        return false;
                    } else if (!ch.validateName(pers.getName())) {
                        System.out.println("В имени не могут содержаться цифры и спец. знаки");
                        return false;
                    } else if (!ch.validatePassport(pers.getPassportID())) {
                        System.out.println("В passport id должны содержаться только цифры.");
                        return false;
                    } else if (pers.getPassportID().length() < 10 || pers.getPassportID().length() > 27) {
                        System.out.println("Passport id должен содержать от 10 до 27 цифр, проверьте длину.");
                        return false;
                    }
                    else {
                        pers.setTime();
                        pers.setID();
                        people.add(pers);
                        Location currentLocation = pers.getLocation();
                        for (Location l: readyLocations.values()){
                            if (currentLocation.equals(l)) {
                                alreadyLocation=true;
                                break;
                            }
                        }
                        if (!alreadyLocation) readyLocations.put(readyLocations.size()+1,pers.getLocation());
                        //System.out.println("Элемент "+pers.getName()+" добавлен в коллекцию");
                        Collections.sort(people);
                        int numer = people.indexOf(pers);
                        ch.addLastPersonNum(numer);
                        return true;
                    }
                }catch (JsonSyntaxException e) {
                    System.out.println("Проверьте формат ввода строки json");
                    return false;
                }
                catch (Exception e){
                    System.out.println("Ошибка при чтении объекта из строки");
                    return false;
                }
            } else {
                System.out.println("Неверный формат строки: должна быть непустой, в фигурных скобках");
                return false;
            }
        }
        else {
            System.out.println("У команды add должен быть только один аргумент.");
            return false;
        }
    }

    public void addLocation(BufferedReader br, CommandHandler ch) throws IOException {
        Location l = new Location();
        int x;
        float y;
        double z;
            try {
                System.out.println("Введите координату х. Это обязательное поле.");
                x=enterSomeNumber(br);

                System.out.println("Введите координату y. Это обязательное поле.");
                y=(float) enterSomeNumber(br);

                System.out.println("Введите координату z. Это обязательное поле.");
                z=enterSomeNumber(br);

                System.out.println("По желанию назовите ваше местоположение.");
                System.out.print(">");
                String i = br.readLine().trim();
                l.setLocation(x,y,z,i);
                ch.addLocation(l);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода, попробуйте ввести координаты еще раз");
            }
    }

    private int enterSomeNumber(BufferedReader br) {
        int x;
        do{
            try {
                System.out.print(">");
                String i = br.readLine().trim();
                if (i.isEmpty())
                    System.out.println("Нельзя ввести пустую строку в это поле, пожалуйста, введите число.");
                else {
                    x = Integer.parseInt(i);
                    break;
                }
            }catch (Exception e){
                System.out.println("Неверный формат ввода координаты, введите число еще раз.");
            }
        } while (true);
        return x;
    }

    public int getLastPerson(){
        return people.indexOf(this.p);
    }

    public String getName() {
        return "add";
    }

    public String getDescription() {
        return "add Person : добавить новый элемент в коллекцию";
    }

    private void inputName(BufferedReader br, Person p){
        do {
            try {
                System.out.print(">");
                String name = br.readLine().trim();
                boolean hasNoDigit = ch.validateName(name);
                if (name.equals(""))
                    System.out.println("Нельзя ввести пустую строку в это поле, пожалуйста, введите данные.");
                else if (!hasNoDigit) System.out.println("В имени не можгут содержаться цифры и спец. знаки");
                else {
                    p.setName(name);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Неверный формат ввода строки. Попробуйте ввести имя ещё раз.");
            }
        }while (true);
    }

    public void setInputName(BufferedReader br, Person p){
        inputName(br, p);
    }

    private void inputHeight(BufferedReader br, Person p){
        do {
            try {
                System.out.print(">");
                String i = br.readLine().trim();
                if (!i.isEmpty()) {
                    long height = Long.parseLong(i);
                    if (height <= 0) {
                        do {
                            System.out.println("Нельзя ввести число меньше нуля, пожалуйста, введите число еще раз.");
                            System.out.print(">");
                            i = br.readLine().trim();
                            height = Long.parseLong(i);
                        } while (height <= 0);
                    } else {
                        p.setHeight(height);
                        break;
                    }
                }break;
            } catch (Exception e) {
                System.out.println("Неверный формат ввода. Введите число или enter");
            }
        }while (true);
    }

    public void setInputHeight(BufferedReader br, Person p){
        inputHeight(br, p);
    }

    private void inputWeight(BufferedReader br, Person p){
        do{
            try {
                System.out.print(">");
                String i = br.readLine().trim();
                if (!i.isEmpty()) {
                    long weight = Long.parseLong(i);
                    if (weight <= 0) {
                        do {
                            System.out.println("Нельзя ввести число меньше нуля, пожалуйста, введите число еще раз.");
                            System.out.print(">");
                            i = br.readLine().trim();
                            weight = Long.parseLong(i);
                        } while (weight <= 0);
                    } else {
                        p.setWeight(weight);
                        break;
                    }
                } break;
            } catch (Exception e) {
                System.out.println("Неверный формат ввода. Введите число или enter");
            }
        } while (true);
    }

    public void setInputWeight(BufferedReader br, Person p){
        inputWeight(br, p);
    }

    private void inputPassport(BufferedReader br, Person p){
        try {
            do {
                System.out.print(">");
                String passport = br.readLine().trim();
                boolean hasNoLetter = ch.validatePassport(passport);
                if (passport.equals(""))
                    System.out.println("Нельзя ввести пустую строку в данное поле, пожалуйста, введите данные.");
                else if (passport.length() > 27 || passport.length() < 10)
                    System.out.println("ID не подходит по длине,попробуйте снова.");
                else if (!hasNoLetter) System.out.println("PassportID должен состоять только из цифр");
                else {
                    p.setPassport(passport);
                    break;
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Неверный формат ввода. Попробуйте ввести ID паспорта ещё раз");
        }
    }

    public void setInputPassport(BufferedReader br, Person p){
        inputPassport(br, p);
    }

    private void inputHairColor(BufferedReader br, Person p){
        do {
            try {
                System.out.println("Пожалуйста, введите цвет волос из представленных (на английском) :\nYELLOW \nORANGE \nWHITE \nBROWN");
                boolean isFound = false;
                do {
                    System.out.print(">");
                    String input = br.readLine().trim();
                    if (input.isEmpty())
                        System.out.println("Нельзя ввести пустую строку в данное поле, пожалуйста, введите данные.");
                    else {
                        for (Color c : Color.values()) {
                            if (input.equalsIgnoreCase(c.toString())) {
                                isFound = true;
                                hair = c;
                                break;
                            }
                        }
                        if (!isFound) System.out.println("Введите вариант из предложенных");
                    }
                } while (!isFound);
                p.setHair(hair);
                break;
            } catch (Exception e) {
                System.out.println("Неверный формат ввода. Попробуйте ввести цвет волос ещё раз");
            }
        }while (true);
    }

    public void setInputHairColor(BufferedReader br, Person p){
        inputHairColor(br, p);
    }

    private void inputLocation(BufferedReader br, Person p, Map<Integer,Location> readyLocations) throws IOException {
        int num = -1;
        if (readyLocations.size() == 0) {
            System.out.println("Пока не существует готовых местоположений. Добавьте одно.");
            addLocation(br, ch);
        }
        do{
            try {
                System.out.println("Введите номер доступного местоположения или добавьте новое: ");
                System.out.println("0. Добавить новое местоположение");
                for (int key : readyLocations.keySet()) {
                    System.out.print(key);
                    Location value = readyLocations.get(key);
                    System.out.println(". " + value.getName() + value.getLocation());
                }
                num = enterSomeNumber(br);
                if (num == 0) {
                    addLocation(br, ch);
                }
                if (num < 0 || num > readyLocations.size()) {
                    System.out.println("Был введен неправильный номер. Введите номер от 0 до " + readyLocations.size());
                    num = 0;
                }
            } catch (Exception e) {
                System.out.println("Неверный формат ввода. Попробуйте ввести расположение ещё раз");
                num=0;
            }
        } while (num == 0);
        Location location = readyLocations.get(num);
        p.setLocation(location);
    }

    public void setInputLocation(BufferedReader br, Person p, Map<Integer,Location> readyLocations) throws IOException {
        inputLocation(br, p, readyLocations);
    }

    private void inputCoordinates(BufferedReader br, Person p){
        Coordinates c = new Coordinates();
        System.out.println("Введите координату х. Это обязательное поле.");
        float x = (float) enterSomeNumber(br);
        System.out.println("Введите координату y. Это обязательное поле.");
        double y = enterSomeNumber(br);
        c.setCoordinatesFirst(x, y);
        p.setCoordinates(c);
    }

    public void setInputCoords(BufferedReader br, Person p){
        inputCoordinates(br, p);
    }
}
