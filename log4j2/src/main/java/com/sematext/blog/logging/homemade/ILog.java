package com.sematext.blog.logging.homemade;


import java.util.concurrent.Callable;
import java.util.function.Supplier;

import static java.lang.String.format;
import static java.lang.System.out;

public interface ILog {
    private <R> R execute(String message, Callable<R> fn) throws Exception {
        var stack = StackWalker.getInstance().walk(frames -> frames.skip(2).findFirst().orElse(null));
        var executedBy = stack != null
                ? format("%s.%s", stack.getClassName(), stack.getMethodName())
                : "NO_METHOD";

        try {
            out.printf("[%s] start %s%n", executedBy, message);
            var output = fn.call();
            out.printf("[%s] finished %s%n", executedBy, message);

            return output;
        } catch (Exception e) {
            out.printf("[%s] failed %s%n", executedBy, message);
            throw e;
        }
    }

    default void log(String message, Runnable fn) throws Exception {
        execute(message, () -> {
            fn.run();
            return null;
        });
    }

    default <R> R log(String message, Supplier<R> fn) throws Exception {
        return execute(message, fn::get);
    }
}