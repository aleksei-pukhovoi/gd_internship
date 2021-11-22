package collections.pattern.ratelimite;

public interface RateLimit {
    public boolean isAllowed(String userId);
}
