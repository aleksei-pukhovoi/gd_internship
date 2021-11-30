package oopDesignPatterns.behavioral.command.command;

import oopDesignPatterns.behavioral.command.receiver.FileSystemReceiver;

public class WriteFileCommand implements Command{
    private FileSystemReceiver fileSystem;

    public WriteFileCommand(FileSystemReceiver fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public void execute() {
        this.fileSystem.writeFile();
    }
}
