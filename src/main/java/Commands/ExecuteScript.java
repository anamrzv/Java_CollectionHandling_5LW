package Commands;

import Other.*;

import java.io.*;
import java.util.*;

/** Команда получает текстовый файл со скриптом и выполняет команды оттуда */
public class ExecuteScript extends Command {

    /** Конструктор - создание нового объекта
     * @param ch - обработчик команд
     */
    public ExecuteScript(CommandHandler ch){
        super(ch);
    }

    /** Главный метод класса, запускает команду
     * @param args Параметры командной строки
     * @return true/false Успешно ли завершилась команда
     */
    @Override
    public boolean execute(String... args){
            if (args.length == 1) {
                File file = new File(args[0]);
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    Map<String, Command> commands = ch.getMap();
                    String line = br.readLine().trim();
                    while (line != null) {
                        if (line.equals("execute_script "+args[0])){
                            System.out.println("Обнаружена рекурсия, удалите строку execute_script "+args[0]);
                            return false;
                        }
                        else {
                            String cmd = ch.parseCommand(line);
                            String[] arguments = ch.getArguments(line);
                            Command command = commands.get(cmd);
                            command.execute(arguments);
                            System.out.println("");
                            line = br.readLine().trim();
                        }
                    }
                    return true;
                }catch (FileNotFoundException e) {
                    System.out.println("Файл с таким названием не найден. Убедитесь, что вы правильно указали название файла и введите команду снова.");
                    return false;
                }catch (Exception e) {
                    System.out.println("Произошла ошибка при чтении скрипта. Проверьте корректность скрипта.");
                    return false;
                }
            }

            else {
                System.out.println("У команды execute_script должен быть один аргумент - имя файла. Введите команду снова.");
                return false;
            }
        }

    /** Возвращает имя команды
     * @return имя
     */
    @Override
    public String getName() {
        return "execute_script";
    }

    /** Возвращает описание команды
     * @return описание
     */
    @Override
    public String getDescription() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором они вводятся в интерактивном режиме.";
    }
}
