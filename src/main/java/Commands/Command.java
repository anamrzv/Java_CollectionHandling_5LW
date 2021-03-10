package Commands;

import Other.CommandHandler;

import java.io.IOException;

/** Абстрактный класс - любая команда */
public abstract class Command {

    /** Поле - обработчик команд */
    public CommandHandler ch;

    /** Конструктор - создание нового объекта
     * @param ch - обработчик команд
     */
    Command(CommandHandler ch){
        this.ch=ch;
    }

    /** Главный метод класса, запускает команду
     * @param args Параметры командной строки
     * @return true/false Успешно ли завершилась команда
     */
    public abstract boolean execute(String... args) throws IOException;

    /** Возвращает имя команды
     * @return имя
     */
    public abstract String getName();

    /** Возвращает описание команды
     * @return описание
     */
    public abstract String getDescription();
}
