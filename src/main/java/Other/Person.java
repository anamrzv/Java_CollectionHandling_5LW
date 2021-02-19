package Other;

public class Person {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long height; //Значение поля должно быть больше 0
    private long weight; //Значение поля должно быть больше 0
    private String passportID; //Строка не может быть пустой, Длина строки должна быть не меньше 10, Длина строки не должна быть больше 27, Поле не может быть null

    private Color hairColor; //Поле не может быть null
    private Location location; //Поле может быть null
    private Coordinates coordinates; //Поле не может быть null
}
