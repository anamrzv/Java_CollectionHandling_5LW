package Commands;

public class SimpleAdd implements Command{

    public void run() {

    }

    public String getName() {
        return "add {element}";
    }

    public String getDescription() {
        return " : добавить новый элемент в коллекцию";
    }
}
