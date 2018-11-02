package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * TODO tidy it up a bit.
 */
class ParSort {

    public static int cutoff = 1000;

    public static void sort(int[] array, int from, int to) {
        int size = to - from;
        if (size < cutoff) {
            Arrays.sort(array, from, to);
        } else {
//            CompletableFuture<int[]> parsort1 = parsort(array, from, from + (to - from) / 4); // TODO implement me
//            CompletableFuture<int[]> parsort2 = parsort(array, from + (to - from) / 4, from + (to - from) / 2); // TODO implement me
//            CompletableFuture<int[]> parsort3 = parsort(array, from + (to - from) / 2, from + (to - from) * 3/ 4); // TODO implement me
//            CompletableFuture<int[]> parsort4 = parsort(array, from + (to - from) * 3/ 4, to); // TODO implement me
            CompletableFuture<int[]> parsort1 = parsort(array, from, from + (to - from) / 8); // TODO implement me
            CompletableFuture<int[]> parsort2 = parsort(array, from + (to - from) / 8, from + (to - from) / 4); // TODO implement me
            CompletableFuture<int[]> parsort3 = parsort(array, from + (to - from) / 4, from + (to - from) * 3/ 8); // TODO implement me
            CompletableFuture<int[]> parsort4 = parsort(array, from + (to - from) * 3/ 8, from + (to - from) / 2); // TODO implement me
            CompletableFuture<int[]> parsort5 = parsort(array, from + (to - from) / 2, from + (to - from) * 5/ 8); // TODO implement me
            CompletableFuture<int[]> parsort6 = parsort(array, from + (to - from) * 5/ 8, from + (to - from) *3/ 4); // TODO implement me
            CompletableFuture<int[]> parsort7 = parsort(array, from + (to - from) * 3/ 4, from + (to - from) * 7/ 8); // TODO implement me
            CompletableFuture<int[]> parsort8 = parsort(array, from + (to - from) * 7/ 8, to); // TODO implement me
            CompletableFuture<int[]> p12 = merge(parsort1,parsort2);
            CompletableFuture<int[]> p34 = merge(parsort3,parsort4);
            CompletableFuture<int[]> p56 = merge(parsort5,parsort6);
            CompletableFuture<int[]> p78 = merge(parsort7,parsort8);
            CompletableFuture<int[]> p1234 = merge(p12,p34);
            CompletableFuture<int[]> p5678 = merge(p56,p78);
            CompletableFuture<int[]> parsort = merge(p1234,p5678);
            parsort.whenComplete((result, throwable) -> System.arraycopy(result, 0, array, from, result.length));
//            System.out.println("# threads: "+ ForkJoinPool.commonPool().getRunningThreadCount());
            parsort.join();
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int[] result = new int[to - from];
                    // TODO implement me
                    System.arraycopy(array, from, result, 0, result.length);
                    sort(result, 0, to - from);
                    return result;
                }
        );
    }
    
    private static CompletableFuture<int[]> merge(CompletableFuture<int[]> t1,CompletableFuture<int[]> t2){
        CompletableFuture<int[]> parsort = t1.
                    thenCombine(t2, (xs1, xs2) -> {
                        int[] result = new int[xs1.length + xs2.length];
                        // TODO implement me
                        int i = 0;
                        int j = 0;
                        for (int k = 0; k < result.length; k++) {
                            if (i >= xs1.length) {
                                result[k] = xs2[j++];
                            } else if (j >= xs2.length) {
                                result[k] = xs1[i++];
                            } else if (xs2[j] < xs1[i]) {
                                result[k] = xs2[j++];
                            } else {
                                result[k] = xs1[i++];
                            }
                        }
                        return result;
                    });
        return parsort;
    }
}