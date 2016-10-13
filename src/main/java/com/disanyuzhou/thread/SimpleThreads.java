package com.disanyuzhou.thread;

/**
 * Created by make on 6/12/16.
 */
public class SimpleThreads {

    static void threadMessage(String message){
        System.out.println(Thread.currentThread().getName() + ":  " + message);
    }

    private static class MessageLoop implements Runnable
    {

        public void run() {
            String[] info = {
                    "Mares eat oats",
                    "Does eat oats",
                    "Little lambs eat ivy",
                    "A kid will eat ivy too"
            };

            try{
                for (int i = 0; i < info.length; i++){
                    Thread.sleep(2000);
                    threadMessage(info[i]);
                }
            } catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        long patience = 1000 * 1;

        if (args.length > 0) {
            try {
                patience = Long.parseLong(args[0]) * 1000;
            } catch (NumberFormatException e) {
                System.err.println("Argument must be an integer.");
                System.exit(1);
            }
        }

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Waiting for MessageLoop thread to finish");
        // loop until MessageLoop
        // thread exits
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            // Wait maximum of 1 second
            // for MessageLoop thread
            // to finish.
            t.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience)
                    && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                // Shouldn't be long now
                // -- wait indefinitely
//                t.join();
            }
        }
        threadMessage("Finally!");


    }
}
