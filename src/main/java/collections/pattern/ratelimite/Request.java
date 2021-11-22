package collections.pattern.ratelimite;

import java.time.Instant;
import java.util.Objects;

public class Request {
    private int count;
    private Instant startTime;

    public Request(int count, Instant startTime) {
        this.count = count;
        this.startTime = startTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return count == request.count && Objects.equals(startTime, request.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, startTime);
    }
}
