package com.cuiyansong.benchmark.demo.stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@State(Scope.Benchmark)
public class FilterPerfTest {

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  @Benchmark
  @BenchmarkMode({Mode.Throughput, Mode.AverageTime, Mode.SampleTime})
  @OutputTimeUnit(value = TimeUnit.SECONDS)
  public void should_log_performance_of_stream_filter() {
    assertEquals(true, true);
  }
}
