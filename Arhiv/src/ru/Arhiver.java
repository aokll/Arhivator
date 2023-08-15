package ru;

import ru.command.ExitCommand;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Arhiver {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ведите полный путь к архиву(нужно создать архив .zip или .rar), в котором будут храниться сжатые данные файла");
        Path path = Paths.get(scanner.next());
        //d:\zip\test.zip
        System.out.println("введите полный путь к файлу, который будем архивировать");
        Path path1 = Paths.get(scanner.next());
        //d:\zip\file1\text.txt
        ZipFileManager zipFileManager = new ZipFileManager(path);

        zipFileManager.createZip(path1);

        new ExitCommand().execute();


    }
}
