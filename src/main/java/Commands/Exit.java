package Commands;

public class Exit extends Command{

    public Exit(CommandHandler ch){
        super(ch);
    }

    public boolean execute(String... args){
        if (args==null) {
            System.out.println("Программа завершена");
            System.exit(0);
            return true;
        }
        else {
            System.out.println("У команды exit нет аргументов. Повторите ввод.");
            return false;
        }
    }

    public String getName(){
        return "exit";
    }

    public String getDescription(){
        return "exit : завершить программу без сохранения в файл";
    }
}
