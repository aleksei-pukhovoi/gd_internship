package oopDesignPatterns.behavioral.memento.originator;

import oopDesignPatterns.behavioral.memento.memento.Memento;

import java.time.LocalDateTime;

public class TextFile {
    private String version;
    private String name;
    private int words;
    private LocalDateTime date;

    public TextFile(String version, String name, int words, LocalDateTime date) {
        this.version=version;
        this.name = name;
        this.words = words;
        this.date = date;
    }

    public void setMemento(Memento memento) {
        version=memento.getVersion();
        name = memento.getName();
        words = memento.getWords();
        date = memento.getDate();
    }

    public Memento getMemento() {
        Memento memento = new Memento();
        memento.setVersion(version);
        memento.setName(name);
        memento.setWords(words);
        memento.setDate(date);
        return memento;
    }

    @Override
    public String toString() {
        return "TextFile{" +
                "name='" + name + '\'' +
                ", words=" + words +
                ", date=" + date +
                '}';
    }
}
