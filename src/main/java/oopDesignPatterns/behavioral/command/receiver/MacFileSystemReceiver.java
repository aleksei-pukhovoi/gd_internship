package oopDesignPatterns.behavioral.command.receiver;

public class MacFileSystemReceiver implements FileSystemReceiver{
    @Override
    public void openFile() {
        System.out.println("Open file in Mac OS");
    }

    @Override
    public void writeFile() {
        System.out.println("Write file in Mac OS");
    }

    @Override
    public void closeFile() {
        System.out.println("Close file in Mac OS");
    }
}
