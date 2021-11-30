package oopDesignPatterns.behavioral.command.receiver;

public class WindowsFileSystemReceiver implements FileSystemReceiver{
    @Override
    public void openFile() {
        System.out.println("Open file in Windows OS");
    }

    @Override
    public void writeFile() {
        System.out.println("Write file in Windows OS");
    }

    @Override
    public void closeFile() {
        System.out.println("Close file in Windows OS");
    }
}
