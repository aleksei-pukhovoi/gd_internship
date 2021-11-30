package oopDesignPatterns.behavioral.command;

import oopDesignPatterns.behavioral.command.command.CloseFileCommand;
import oopDesignPatterns.behavioral.command.command.OpenFileCommand;
import oopDesignPatterns.behavioral.command.command.WriteFileCommand;
import oopDesignPatterns.behavioral.command.invoker.FileInvoker;
import oopDesignPatterns.behavioral.command.receiver.FileSystemReceiver;
import oopDesignPatterns.behavioral.command.receiver.FileSystemReceiverUtil;

public class Main {
    public static void main(String[] args) {
        FileSystemReceiver fs = FileSystemReceiverUtil.getUnderlyingFileSystem();
        OpenFileCommand open = new OpenFileCommand(fs);
        FileInvoker file = new FileInvoker(open);
        file.execute();

        WriteFileCommand write = new WriteFileCommand(fs);
        FileInvoker file2 = new FileInvoker(write);
        file2.execute();

        CloseFileCommand close = new CloseFileCommand(fs);
        FileInvoker file3 = new FileInvoker(close);
        file3.execute();
    }
}
