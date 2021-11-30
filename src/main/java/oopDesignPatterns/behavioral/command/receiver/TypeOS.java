package oopDesignPatterns.behavioral.command.receiver;

public enum TypeOS {
    WINDOWS{
        @Override
        FileSystemReceiver getFileSystemReceiver() {
            return new WindowsFileSystemReceiver();
        }
    },
    MAC{
        @Override
        FileSystemReceiver getFileSystemReceiver() {
            return new MacFileSystemReceiver();
        }
    },
    UNIX{
        @Override
        FileSystemReceiver getFileSystemReceiver() {
            return new UnixFileSystemReceiver();
        }
    };

    abstract FileSystemReceiver getFileSystemReceiver();
}
