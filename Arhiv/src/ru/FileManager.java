package ru;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private Path rootPath;// - корневой путь директории, файлы которой нас интересуют
    private List<Path> fileList;//список относительных путей файлов внутри rootPath

    public FileManager(Path rootPath) throws IOException {
        this.rootPath = rootPath;
        this.fileList = new ArrayList<>();
        collectFileList(rootPath);
    }

    public List<Path> getFileList() {
        return this.fileList;
    }

    private void collectFileList(Path path) throws IOException{//алгоритм, который обходит дерево файлов.
        if (Files.isRegularFile(path)){//если переданный путь path является обычным файлом
            fileList.add(rootPath.relativize(path));//добавляется файл(path) в fileList относительно пути rootPath
        } else if (Files.isDirectory(path)) {//Если переданный путь path, является директорией
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path))//Получаем объект со списком файлов
            {
                for (Path p : directoryStream) {//aобходим этот обьект и рекурсивно вызываем метод collectFileList(p)
                    //передавая туда файл, при повторном вызове метода collectFileList(p) этот файл
                    //запишется в fileList.
                    collectFileList(p);
                }
            }

        }
    }
}