package threadSafety.rateLimiter;

import collections.pattern.ratelimite.RateLimit;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SlidingWindowParallel implements RateLimit {
    private final Map<String, Set<Instant>> map;
    private final int requestLimit;
    private final ChronoUnit requestedTimeUnit;

    public SlidingWindowParallel(int requestLimit, ChronoUnit requestedTimeUnit) {
        this.map = new HashMap<>() {
        };
        this.requestLimit = requestLimit;
        this.requestedTimeUnit = requestedTimeUnit;
    }

    @Override
    public boolean isAllowed(String userId) {
        synchronized (this) {
            if (!map.containsKey(userId)) {
                Set<Instant> set = new HashSet<>();
                set.add(Instant.now());
                map.put(userId, set);
                return true;
            }
        }
        Instant instant = Instant.now();
        Instant currentTime = instant.minus(1, requestedTimeUnit);

        synchronized (this) {
            Set<Instant> instants = map.get(userId).stream()
                    .filter(i -> i.compareTo(currentTime) > 0)
                    .collect(Collectors.toSet());
            if (instants.size() > requestLimit - 1) {
                return false;
            }
            instants.add(Instant.now());
            map.put(userId, instants);
            return true;
        }
    }


}
