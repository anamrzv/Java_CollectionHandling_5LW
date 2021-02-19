package Other;
import Commands.*;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args){
        LinkedList<Person> collection = new LinkedList<Person>();
        CommandHandler c = new CommandHandler();
        c.setStart();
    }
}
