package collections.pattern.ratelimite;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class SlidingWindow implements RateLimit{
    private final Map<String, Set<Instant>> map;
    private final int requestLimit;
    private final ChronoUnit requestedTimeUnit;

    public SlidingWindow(int requestLimit, ChronoUnit requestedTimeUnit) {
        this.map = new HashMap<>();
        this.requestLimit = requestLimit;
        this.requestedTimeUnit = requestedTimeUnit;
    }

    @Override
    public boolean isAllowed(String userId) {
        if (map.containsKey(userId)) {
            Instant instan =   Instant.now();
            Instant currentTime = instan.minus(1, requestedTimeUnit);
            Set<Instant> instants = map.get(userId).stream()
                    .filter(instant -> instant.compareTo(currentTime) > 0)
                    .collect(Collectors.toSet());
            if (instants.size() > requestLimit - 1) {
                return false;
            }
                instants.add(Instant.now());
                map.put(userId,instants);
                return true;
            }
        Set<Instant> set = new HashSet<>();
        set.add(Instant.now());
        map.put(userId, set);
        return true;
    }
}