import java.util.concurrent.CompletableFuture;

/* Name of the class has to be "Main" only if the class is public. */
class CompletableFutureDemo {
    public static CompletableFuture<Integer> getSquare(int num) {
        try {
            Thread.sleep(1000);
            int res = num * num;
            return CompletableFuture.supplyAsync(() -> res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CompletableFuture<Integer> getCube(int num) {
        try {
            Thread.sleep(1000);
            int res = num * num * num;
            return CompletableFuture.supplyAsync(() -> res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        getSquare(10).thenApply(number -> number * 2)
                .thenApply(number -> number + 10)
                .thenAccept(System.out::println)
                .thenRun(() -> {
                    System.out.println("CompletableFuture never dies");
                    System.out.println("Sleep 2 sec");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        getCube(10).thenApply(number -> number * 2)
                .thenApply(number -> number + 10)
                .thenAccept(System.out::println)
                .thenRun(() -> {
                    System.out.println("CompletableFuture never dies");
                    System.out.println("Sleep 2 sec");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        System.out.println("Execution is not blocked by thenApply");

        // int x = getSquare(10).get();
        // System.out.println(x);
        // System.out.println("run here late because it was blocked by get square");

    }
}
