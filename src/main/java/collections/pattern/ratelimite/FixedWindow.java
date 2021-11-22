package collections.pattern.ratelimite;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FixedWindow implements RateLimit{
    private final Map<String, Request> map;
    private final int requestLimit;
    private final ChronoUnit requestedTimeUnit;

    public FixedWindow(int requestLimit, ChronoUnit timeUnit) {
        map = new HashMap<>();
        this.requestLimit = requestLimit;
        this.requestedTimeUnit = timeUnit;
    }

    @Override
    public boolean isAllowed(String userId) {
        if (map.containsKey(userId)) {
            Request request = map.get(userId);
            long timeFromTimeUnit = getTime(requestedTimeUnit);
            Instant currentTime = Instant.now();
            if ((currentTime.toEpochMilli() - request.getStartTime().toEpochMilli()) >= timeFromTimeUnit) {
                request.setStartTime(currentTime);
                request.setCount(1);
                return true;
            } else {
                int currentCount = request.getCount();
              if (currentCount < requestLimit) {
                  request.setCount(currentCount + 1);
                  return true;
              } else {
                  return false;
              }
            }
        }
        map.put(userId, new Request(1, Instant.now().truncatedTo(requestedTimeUnit)));
        return true;
    }

    private long getTime(ChronoUnit unit) {
        return TimeUnit.of(unit).toMillis(1);
    }
}
