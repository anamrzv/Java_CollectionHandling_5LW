package Commands;

import Other.*;

import java.io.*;
import java.util.*;

public class SimpleAdd implements Command{

    private Map<Integer,Location> readyLocations;
    private Color hair;

    public void run(CommandHandler ch) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Person p = new Person();
        readyLocations = ch.getLocations();

        System.out.println("Введите имя персоны (не пустая строка). Это обязательное поле");
        try {
            do {
                System.out.print(">");
                String name = br.readLine().trim();
                if (name.equals(""))
                    System.out.println("Нельзя ввести пустую строку в это поле, пожалуйста, введите данные.");
                else {
                    p.setName(name);
                    break;
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Неверный формат ввода. Попробуйте ввести имя ещё раз.");
        }

        System.out.println("Введите рост. Он должен быть больше 0. Это необязательное поле. ");
        try {
            System.out.print(">");
            String i = br.readLine().trim();
            if (!i.isEmpty()) {
                long height = Long.parseLong(i);
                if (height<=0) {
                    do {
                        System.out.println("Нельзя ввести число меньше нуля, пожалуйста, введите число еще раз.");
                        System.out.print(">");
                        i = br.readLine().trim();
                        height = Long.parseLong(i);
                    } while (height<=0);
                }
                else p.setHeight(height);
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ввода. Попробуйте ввести рост ещё раз");
        }

        System.out.println("Введите вес. Он должен быть больше 0. Это необязательное поле");
        try {
            System.out.print(">");
            String i = br.readLine().trim();
            if (!i.isEmpty()) {
                long weight = Long.parseLong(i);
                if (weight<=0) {
                    do {
                        System.out.println("Нельзя ввести число меньше нуля, пожалуйста, введите число еще раз.");
                        System.out.print(">");
                        i = br.readLine().trim();
                        weight = Long.parseLong(i);
                    } while (weight<=0);
                }
                else p.setWeight(weight);
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ввода. Попробуйте ввести вес ещё раз");
        }

        System.out.println("Введите ID паспорта. Длина ID должна лежать в диапазоне [10;27]. Это обязательное поле");
        try {
            do {
                System.out.print(">");
                String passport = br.readLine().trim();
                if (passport.equals(""))
                    System.out.println("Нельзя ввести пустую строку в данное поле, пожалуйста, введите данные.");
                else if (passport.length() > 27 || passport.length() < 10)
                    System.out.println("ID не подходит по длине,попробуйте снова.");
                else {
                    p.setPassport(passport);
                    break;
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Неверный формат ввода. Попробуйте ввести ID паспорта ещё раз");
        }

        System.out.println("Выберите цвет волос. Это обязательное поле.");
        try {
            do {
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
                }while (!isFound);
                p.setHair(hair);
                break;
            } while (true);
        } catch (Exception e) {
            System.out.println("Неверный формат ввода. Попробуйте ввести цвет волос ещё раз");
        }

        System.out.println("Выберите местоположение персоны из существующих. Это обязательное поле.");
        try {
                {
                    if (readyLocations.size() == 0) {
                    System.out.println("Пока не существует готовых местоположений. Добавьте одно.");
                    addLocation(br, ch); }
                }

                int num=-1;
                do {
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
                    if (num < 0 || num > readyLocations.size())
                    {
                        System.out.println("Был введен неправильный номер. Введите номер от 0 до " + readyLocations.size());
                        num=0;
                    }
                }while (num==0);
                Location location = readyLocations.get(num);
                p.setLocation(location);
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ввода. Попробуйте ввести расположение ещё раз");
        }

        System.out.println("Введите координаты персоны. Это обязательное поле.");
        try {
            Coordinates c = new Coordinates();
            System.out.println("Введите координату х. Это обязательное поле.");
            float x = (float) enterSomeNumber(br);
            System.out.println("Введите координату y. Это обязательное поле.");
            double y = enterSomeNumber(br);
            c.setCoordinatesFirst(x, y);
            p.setCoordinates(c);
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ввода. Попробуйте ввести расположение ещё раз");
        }

        System.out.println("Вы успешно добавили в коллекцию элемент Person "+p.getName());
        p.setTime();
        p.setID();
        ch.addPerson(p);

    }


    /*private Color color;
    private Color parseHair(int hair){
            switch (hair){
                case (1):
                    color= Color.YELLOW;
                    break;
                case (2):
                    color= Color.ORANGE;
                    break;
                case (3):
                    color= Color.WHITE;
                    break;
                case (4):
                    color= Color.BROWN;
                    break;
            }
            return color;
    }*/

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
                //readyLocations.put(readyLocations.size()+1, l);
                return;
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода, попробуйте ввести координаты еще раз");
            }
    }


    private int enterSomeNumber(BufferedReader br) throws IOException {
        int x;
        do{
            System.out.print(">");
            String i = br.readLine().trim();
            if (i.isEmpty()) System.out.println("Нельзя ввести пустую строку в это поле, пожалуйста, введите число.");
            else {
                x = Integer.parseInt(i);
                break;
            }
        } while (true);
        return x;
    }

    public String getName() {
        return "add";
    }

    public String getDescription() {
        return "add Person : добавить новый элемент в коллекцию";
    }

}
