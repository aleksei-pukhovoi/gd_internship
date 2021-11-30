package oopDesignPatterns.behavioral.command.receiver;

public interface FileSystemReceiver {
    void openFile();
    void writeFile();
    void closeFile();
}
