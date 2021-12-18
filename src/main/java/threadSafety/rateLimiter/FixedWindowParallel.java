package threadSafety.rateLimiter;

import collections.pattern.ratelimite.RateLimit;
import collections.pattern.ratelimite.Request;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class FixedWindowParallel implements RateLimit {
    private final Map<String, Request> map;
    private final int requestLimit;
    private final ChronoUnit requestedTimeUnit;

    public FixedWindowParallel(int requestLimit, ChronoUnit timeUnit) {
        map = new HashMap<>();
        this.requestLimit = requestLimit;
        this.requestedTimeUnit = timeUnit;
    }

    @Override
    public boolean isAllowed(String userId) {
        synchronized (this) {
            if (!map.containsKey(userId)) {
                map.put(userId, new Request(1, Instant.now().truncatedTo(requestedTimeUnit)));
                return true;
            }
        }
        long timeFromTimeUnit = getTime(requestedTimeUnit);
        Instant currentTime = Instant.now();
        Request  request = map.get(userId);
        synchronized (this) {
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
    }

    private long getTime(ChronoUnit unit) {
        return TimeUnit.of(unit).toMillis(1);
    }
}
