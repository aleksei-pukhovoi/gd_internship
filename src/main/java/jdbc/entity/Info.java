package jdbc.entity;

import java.time.Instant;

public class Info extends Entity{

    private int id;
    private Student student;
    private Instant startTime;
    private int tasksDone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public int getTasksDone() {
        return tasksDone;
    }

    public void setTasksDone(int tasksDone) {
        this.tasksDone = tasksDone;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", student=" + student +
                ", startTime=" + startTime +
                ", tasksDone=" + tasksDone +
                '}';
    }
}
