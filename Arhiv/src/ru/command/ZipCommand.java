package ru.command;

import ru.ConsoleHelper;
import ru.ZipFileManager;

import java.nio.file.Paths;

public abstract class ZipCommand implements Command {

    @Override
    public void execute() throws Exception {

    }

    public ZipFileManager getZipFileManager() throws Exception {
        System.out.println("¬ведите полный путь файла архива");
        return new ZipFileManager(Paths.get(ConsoleHelper.readString())) ;
    }
}
