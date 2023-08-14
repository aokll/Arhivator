import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Arhiver {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ведите полный путь к архиву, в котором будут храниться сжатые данные файла");
        Path path = Paths.get(scanner.next());
        System.out.println("введите полный путь к файлу, который будем архивировать");
        Path path1 = Paths.get(scanner.next());

        ZipFileManager zipFileManager = new ZipFileManager(path);

        zipFileManager.createZip(path1);



    }
}
