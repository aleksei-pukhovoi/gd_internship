package oopDesignPatterns.behavioral.command.receiver;

public class UnixFileSystemReceiver implements FileSystemReceiver{
    @Override
    public void openFile() {
        System.out.println("Open file in Unix OS");
    }

    @Override
    public void writeFile() {
        System.out.println("Write file in Unix OS");
    }

    @Override
    public void closeFile() {
        System.out.println("Close file in Unix OS");
    }
}
