package org.ibs.cds.gode.util;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;

public class Promise<V> extends CompletableFuture<V> {
    private Future<V> future;

    public Promise(Future<V> future) {
        this.future = future;
        Context.schedule(this::tryToComplete);
    }

    private void tryToComplete() {
        if (future.isDone()) {
            try {
                complete(future.get());
            } catch (InterruptedException e) {
                completeExceptionally(e);
            } catch (ExecutionException e) {
                completeExceptionally(e.getCause());
            }
            return;
        }

        if (future.isCancelled()) {
            cancel(true);
            return;
        }

        Context.schedule(this::tryToComplete);
    }

    public static class Context {
        private static final ScheduledExecutorService SERVICE = Executors.newSingleThreadScheduledExecutor();

        public static void schedule(Runnable r) {
            SERVICE.schedule(r, 1, TimeUnit.MILLISECONDS);
        }
    }
}
