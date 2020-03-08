package com.cuiyansong.benchmark.demo.stream;

import com.cuiyansong.benchmark.demo.DemoApplication;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@Warmup(iterations = 3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class FilterPerf {

  private Integer[] collections = new Integer[1000 * 1000];

  public static void main(String[] args) throws RunnerException {
    SpringApplication.run(DemoApplication.class, args);
    Options opt = new OptionsBuilder().include(FilterPerf.class.getSimpleName()).forks(1).build();
    new Runner(opt).run();
  }

  @Setup
  public void InitCollection() {
    for (int i = 0; i < collections.length; i++) {
      collections[i] = i;
    }
  }

  @Benchmark
  public Object[] measureStreamFilterOneByOne() {
    return Arrays.stream(collections)
        .filter(i -> i % 2 == 0)
        .filter(i -> i % 5 == 0)
        .filter(i -> i % 3 == 0)
        .limit(1000)
        .toArray();
  }

  @Benchmark
  public Object[] measureStreamFilterOnlyOne() {
    return Arrays.stream(collections)
        .filter(i -> i % 5 == 0 && i % 2 == 0 && i % 3 == 0)
        .limit(1000)
        .toArray();
  }
}
