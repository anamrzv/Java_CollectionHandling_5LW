package Other;

import java.io.*;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.stream.*;

public class DocumentHandler {

    private void read() throws IOException {
        String homeDir = System.getenv("HOME");
        String file = homeDir + "/Programming/file.json"; // чтобы открывалось с любого компьютера

        FileReader fr = new FileReader(file);
        Scanner scan = new Scanner(fr);


        while (scan.hasNextLine()) {

        }

        fr.close();
    }

}
