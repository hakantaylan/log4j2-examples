package com.sematext.blog.logging.mdc;

import java.util.concurrent.*;

import static com.sematext.blog.logging.mdc.MDCHelper.wrapWithMdcContext;

public class MDCAwareThreadPoolExecutor extends ThreadPoolExecutor {
    public MDCAwareThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, corePoolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
    }

    public MDCAwareThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
    //Override constructors which you need

    //Executes the given task sometime in the future.
    @Override
    public void execute(Runnable command) {
        super.execute(wrapWithMdcContext(command));
    }
}