package com.example.java_core.p06_java8.lambda;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class ProcessorImpl implements Processor{

    @Override
    public String process(Callable<String> c) throws Exception {
        return "Callable";
    }

    @Override
    public String process(Supplier<String> s) {
        return "Supplier";
    }
}
