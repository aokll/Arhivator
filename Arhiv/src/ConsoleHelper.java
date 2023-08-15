import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    static void writeMessage(String message){//вывести сообщение в косоль
        System.out.println(message);
    }
    static String readString() throws IOException {//прочитать строку с консоли
        String text = null;
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in)))
        {
            text = bufferedReader.readLine();
        }
        return text;
    }
    static int readInt() throws IOException {//прочитать число с консоли
        int text = 0;
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in)))
        {
            text = Integer.parseInt(bufferedReader.readLine());
        }
        return text;
    }
}
