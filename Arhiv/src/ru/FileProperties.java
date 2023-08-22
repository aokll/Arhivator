package ru;

public class FileProperties {//который будет отвечать за свойства каждого файла в архиве.
    //Свойства - это набор, состоящий из: имя файла, размер файла до и после сжатия, метод сжатия.

    private String name;
    private long size;
    private long compressedSize;
    private int compressionMethod;

    public FileProperties(String name, long size, long compressedSize, int compressionMethod) {
        this.name = name;
        this.size = size;
        this.compressedSize = compressedSize;
        this.compressionMethod = compressionMethod;
    }

    public long getCompressionRatio(){return 100 - ((compressedSize * 100) / size);}

    @Override
    public String toString() {
        if (size > 0) {
            return String.format("%s %d Kb (%d Kb) сжатие: %d%%", getName(),getSize()/1024,getCompressedSize()/1024,getCompressionRatio());
        }else return getName();
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public long getCompressedSize() {
        return compressedSize;
    }

    public int getCompressionMethod() {
        return compressionMethod;
    }
}
