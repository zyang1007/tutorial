package com.example.java_core.p06_java8.lambda;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public interface Processor {

    abstract String process(Callable<String> c) throws Exception;

    abstract String process(Supplier<String> s);
}
