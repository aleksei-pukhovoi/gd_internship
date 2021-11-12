package collections.pattern.retry;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class RetryCommand<T> implements Retry<T>{
    private final int maxRetries;
    private final long delay;
    private Set<Class<?>> exceptions;

    private static final long DEFAULT_DELAY = 100;
    private static final int DEFAULT_MAX_RETRIES = 5;

    public RetryCommand() {
        this.maxRetries = DEFAULT_MAX_RETRIES;
        this.delay = DEFAULT_DELAY;
    }

    public RetryCommand(int maxRetries, long delay, Class<?>... exceptions) {
        this.maxRetries = maxRetries;
        this.delay = delay;
        this.exceptions = new HashSet<>(Arrays.asList(exceptions));
    }

    @Override
    public T perform(Supplier<T> function) {
        try {
            return function.get();
        } catch (Exception e) {
            return retry(function, e);
        }
    }

    private T retry(Supplier<T> function, Exception ex) {
        int retryCounter = 0;
        while (retryCounter < maxRetries) {
            try {
                return function.get();
            } catch (Exception e) {
                retryCounter++;
                if (exceptions!=null && exceptions.contains(e.getClass())) {
                    waitNextRetry();
                }
            }
        }
        throw new RuntimeException(ex);
    }

    private void waitNextRetry() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
