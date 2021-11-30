package oopDesignPatterns.behavioral.command.command;

import oopDesignPatterns.behavioral.command.command.Command;
import oopDesignPatterns.behavioral.command.receiver.FileSystemReceiver;

public class OpenFileCommand implements Command {
    private FileSystemReceiver fileSystem;

    public OpenFileCommand(FileSystemReceiver fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public void execute() {
        this.fileSystem.openFile();
    }
}
