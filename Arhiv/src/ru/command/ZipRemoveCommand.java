package ru.command;

import ru.ConsoleHelper;
import ru.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ZipRemoveCommand extends ZipCommand{//Команда удаления файла из архива
    @Override
    public void execute() throws Exception {
        List<Path> list = new ArrayList<>();
        ConsoleHelper.writeMessage("Введите путь к архиву из которого вы собираетесь удалить файл(ы)");
        ZipFileManager zipFileManager = getZipFileManager();
        Path path = Paths.get(ConsoleHelper.readString());
        zipFileManager.removeFile(path);

        ConsoleHelper.writeMessage("Удаление из архива завершено.");
    }
}
