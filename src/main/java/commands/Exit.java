package commands;

import other.CommandHandler;

/**
 * Команда завершает программу
 */
public class Exit extends Command {

    /**
     * Конструктор - создание нового объекта
     *
     * @param ch - обработчик команд
     */
    public Exit(CommandHandler ch) {
        super(ch);
    }

    /**
     * Главный метод класса, запускает команду
     *
     * @param args Параметры командной строки
     * @return true/false Успешно ли завершилась команда
     */
    public boolean execute(String... args) {
        if (args == null) {
            System.out.println("Программа завершена.");
            System.exit(0);
            return true;
        } else {
            System.out.println("У команды exit нет аргументов. Введите команду снова.");
            return false;
        }
    }

    /**
     * Возвращает имя команды
     *
     * @return имя
     */
    public String getName() {
        return "exit";
    }

    /**
     * Возвращает описание команды
     *
     * @return описание
     */
    public String getDescription() {
        return "exit : завершить программу без сохранения в файл";
    }
}
