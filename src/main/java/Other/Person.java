package Other;
import java.time.*;
import java.util.Date;
import java.util.Objects;

public class Person implements Comparable {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long height; //Значение поля должно быть больше 0
    private long weight; //Значение поля должно быть больше 0
    private String passportID; //Строка не может быть пустой, Длина строки должна быть не меньше 10, Длина строки не должна быть больше 27, Поле не может быть null
    private Color hairColor; //Поле не может быть null
    private Location location; //Поле может быть null
    private Coordinates coordinates; //Поле не может быть null

    public void setName(String name){
        this.name=name;
    }

    public String getName() {return name;}

    public void setID(){
        this.id=(long)hashCode();
    }

    public void setTime(){
        LocalDateTime now = LocalDateTime.now();
        creationDate = now;
    }

    public LocalDateTime getTime(){
        return creationDate;
    }

    public void setHeight(Long height){
        this.height=height;
    }

    public void setWeight(Long weight){
        this.weight=weight;
    }

    public void setPassport(String passport){
        this.passportID=passport;
    }

    public void setHair(Color color){
        this.hairColor=color;
    }

    public void setLocation(Location location){
        this.location=location;
    }

    public void setCoordinates(Coordinates coordinates){
        this.coordinates=coordinates;
    }

    @Override
    public int compareTo(Object o) {
        Person tmp = (Person) o;
        if (this.id<tmp.id) return -1;
        else return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return height == person.height &&
                weight == person.weight &&
                id.equals(person.id) &&
                name.equals(person.name) &&
                creationDate.equals(person.creationDate) &&
                passportID.equals(person.passportID) &&
                hairColor == person.hairColor &&
                location.equals(person.location) &&
                coordinates.equals(person.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, creationDate, height, weight, passportID, hairColor, location, coordinates);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", height=" + height +
                ", weight=" + weight +
                ", passportID='" + passportID + '\'' +
                ", hairColor=" + hairColor +
                ", location=" + location +
                ", coordinates=" + coordinates +
                '}';
    }
}
