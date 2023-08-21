package ru;

import ru.exception.PathIsNotFoundException;
import ru.exception.WrongZipFileException;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {
    // Полный путь zip файла
    private final Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

   public List<FileProperties> getFilesList() throws Exception{//Он будет возвращать список файлов
        // в архиве, вернее список свойств этих файлов
        List<FileProperties> list = new ArrayList<>();
        //Внутри метода проверь является ли содержимое zipFile обычным файлом
       // с помощью подходящего метода класса Files. Если это не файл, брось
       // исключение WrongZipFileException()
        if (!Files.isRegularFile(zipFile)){
            throw new WrongZipFileException();
        }
        //Создай входящий поток ZipInputStream, для файла из переменной zipFile
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile)))
        {
            //Пройдись по всем элементам ZipEntry потока ZipInputStream
            ZipEntry zipEntry;
            while ((zipEntry=zipInputStream.getNextEntry())!=null){
                //Для каждого элемента ZipEntry вычитай его содержимое, иначе у нас
                // не будет информации о его размере.
                //Нельзя узнать размер файла в архиве, не вычитав его.
                // Это очень легко сделать с помощью функции copyData, используя
                // временный буфер типа ByteArrayOutputStream.
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                copyData(zipInputStream,baos);
                //Получи имя, размер, сжатый размер и метод сжатия элемента архива.
                // Посмотри, что еще можно узнать о нем.
                FileProperties fileProperties = new FileProperties(zipEntry.getName(), zipEntry.getSize(), zipEntry.getCompressedSize(), zipEntry.getMethod());
                list.add(fileProperties);
            }
        }
        return list;
    }

    public void createZip(Path source) throws Exception {
        // Проверяем, существует ли директория, где будет создаваться архив
        // При необходимости создаем ее
        Path zipDirectory = zipFile.getParent();
        if (Files.notExists(zipDirectory))
            Files.createDirectories(zipDirectory);

        // Создаем zip поток
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile))) {

            if (Files.isDirectory(source)) {
                // Если архивируем директорию, то нужно получить список файлов в ней
                FileManager fileManager = new FileManager(source);
                List<Path> fileNames = fileManager.getFileList();

                // Добавляем каждый файл в архив
                for (Path fileName : fileNames)
                    addNewZipEntry(zipOutputStream, source, fileName);

            } else if (Files.isRegularFile(source)) {

                // Если архивируем отдельный файл, то нужно получить его директорию и имя
                addNewZipEntry(zipOutputStream, source.getParent(), source.getFileName());
            } else {

                // Если переданный source не директория и не файл, бросаем исключение
                throw new PathIsNotFoundException();
            }
        }
    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception {
        Path fullPath = filePath.resolve(fileName);
        try (InputStream inputStream = Files.newInputStream(fullPath)) {
            ZipEntry entry = new ZipEntry(fileName.toString());

            zipOutputStream.putNextEntry(entry);

            copyData(inputStream, zipOutputStream);

            zipOutputStream.closeEntry();
        }
    }

    private void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }
}
