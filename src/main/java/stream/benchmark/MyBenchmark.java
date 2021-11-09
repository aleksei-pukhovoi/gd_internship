package stream.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class MyBenchmark {

    @Param({"10000"})
    private int N;

   private final List<String> countries = List.of("USA", "Great Britain", "Russian Federation", "Germany", "EU");
   private final List<Long> quality = List.of(4L, 5L, 10L, 7L, 9L, 2L, 10L, 15L, 1L, 20L, 10L, 5L, 7L);
   private Map<String,Long> map;

public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder().include(MyBenchmark.class.getName()).forks(1).build();
       new Runner(options).run();
    }

    @Setup
    public void setup() {
        map = getCountriesInfo();
    }

    @Benchmark
    public Optional<String> streamSorted() {
        return map
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .findFirst()
                .map(Map.Entry::getKey);
    }

    @Benchmark
    public Optional<String> streamMax() {
        return map
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    @Benchmark
    public Optional<String> streamMaxComparatorByValueAndByKey() {
        return map
                .entrySet().stream()
                .max((entry1,entry2)->
                        Objects.equals(entry1.getValue(), entry2.getValue()) ?
                                entry2.getKey().length() - entry1.getKey().length() :
                                entry1.getValue().compareTo(entry2.getValue())
                )
                .map(Map.Entry::getKey);
    }

    private Map<String, Long> getCountriesInfo() {
    Random random = new Random(42);
    return IntStream.range(0,N)
            .boxed()
            .collect(Collectors.toMap(
                    integer -> countries.get(random.nextInt(countries.size())) + integer,
                    integer -> quality.get(random.nextInt(quality.size()))
            ));
    }
}