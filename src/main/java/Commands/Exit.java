package Commands;

public class Exit implements Command{
    public void run(){
        System.out.println("Программа завершена");
        System.exit(0);
    }

    public String getName(){
        return "exit";
    }

    public String getDescription(){
        return "exit : завершить программу без сохранения в файл";
    }
}
