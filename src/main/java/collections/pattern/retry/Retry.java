package collections.pattern.retry;

import java.util.function.Supplier;

public interface Retry<T> {

    /**
     * Performs (and retries if failed) a supplied function and returns the result.
     *
     * @param function the {@link Supplier} that should be tried (and retried).
     * @return {@link T} result from the provided {@link Supplier}.
     */
    T perform(Supplier<T> function);
}
