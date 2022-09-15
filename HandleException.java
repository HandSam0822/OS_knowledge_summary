import java.util.concurrent.CompletableFuture;

public class HandleException {

    public static CompletableFuture<Integer> create(int number) {
        return CompletableFuture.supplyAsync(() -> compute(number));
    }

    private static int compute(int number) {

        if (number < 5)
            throw new RuntimeException("something went wrong");
        return number;
    }

    public static void main(String[] args) throws InterruptedException {

        /**
         * each and every operation returns CompletableFuture<T>
         * here compute throws exception, then the next immediate exceptionally block
         * will catch it and perform operation. it ignores all the thenApply and
         * thenAccept methods, Now exceptionHandler might blow up the exception or it
         * can log it and return default data in case of an exception. if it blows up
         * the exception, flow stays in error channel, else it comes back to data
         * channel
         */
        create(3).thenApply(number -> number * 2)
                .thenApply(number -> number + 10)
                .exceptionally(HandleException::handleException2)
                .thenAccept(System.out::println)
                .thenRun(() -> System.out.println("CompletableFuture never dies"))
                .thenRun(() -> System.out.println("Really, CompletableFuture never dies"))
                .exceptionally(HandleException::handleException)
                .thenRun(System.out::println);

        System.out.println("Execution is not blocked by thenApply");

        Thread.sleep(5000);

    }

    // stays in error channel
    private static Void handleException(Throwable throwable) {
        System.out.println("error:" + throwable);
        throw new RuntimeException("We can not handle it");
    }

    // comes back to data channel
    private static int handleException2(Throwable throwable) {
        System.out.println("error:" + throwable);
        return -1;
    }

}