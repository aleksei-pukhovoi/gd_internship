package oopDesignPatterns.behavioral.observer.context;

import java.time.LocalDate;

public class PostEvent {
    private LocalDate date;
    private String name;
    private String content;

    public PostEvent(String name, String content) {
        this.date = LocalDate.now();
        this.name = name;
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
