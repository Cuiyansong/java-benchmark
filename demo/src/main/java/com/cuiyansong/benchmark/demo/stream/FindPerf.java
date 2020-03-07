package com.cuiyansong.benchmark.demo.stream;

import com.cuiyansong.benchmark.demo.DemoApplication;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@State(Scope.Thread)
@Warmup(iterations = 3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class FindPerf {

  private Integer[] collections = new Integer[1000 * 1000];

  public static void main(String[] args) throws RunnerException {
    SpringApplication.run(DemoApplication.class, args);
    Options opt = new OptionsBuilder().include(FindPerf.class.getSimpleName()).forks(1).build();
    new Runner(opt).run();
  }

  @Setup
  public void InitCollection() {
    for (int i = 0; i < collections.length; i++) {
      collections[i] = i;
    }
  }

  @Benchmark
  public Object measureStreamFilterOneByOne() {
    return Arrays.stream(collections).filter(i -> i % 2 == 0).map(i -> i * i).findFirst();
  }

  @Benchmark
  public Object measureStreamFilterOnlyOne() {
    List<Integer> filterAndMappedArr = Arrays.stream(collections).filter(i -> i % 2 == 0).map(i -> i * i).collect(Collectors.toList());
    return filterAndMappedArr.stream().findFirst();
  }
}
