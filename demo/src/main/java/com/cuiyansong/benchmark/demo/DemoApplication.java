package com.cuiyansong.benchmark.demo;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) throws RunnerException {
    SpringApplication.run(DemoApplication.class, args);
    Options opt = new OptionsBuilder().forks(1).build();
    new Runner(opt).run();
  }
}
