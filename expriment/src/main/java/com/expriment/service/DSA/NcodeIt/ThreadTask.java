package com.expriment.service.DSA.NcodeIt;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

class InstantiateUsingOnlyThread {

    public static void main(String[] args)throws InterruptedException, ExecutionException {

        System.out.println("Thread main started");

//        Thread thread1 = new Task1();
        Runnable thread1 = new Task2();
//        thread1.run();
//        thread1.start();
//        thread2.start();

//        task2;

       /* ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> result = executorService.submit(new Task3(50));

        while (!result.isDone()) {
            System.out.println("Task is still processing");
            Thread.sleep(500l);
        }

        System.out.println("Task is finished: " + result.isDone());
        System.out.println("Task result is: " + result.get());
        executorService.shutdown();
        System.out.println("Thread main finished");*/

       /* ExecutorService executorService = Executors.newCachedThreadPool();

        Task4 task4 = new Task4(100000);
        Future<Integer> result = executorService.submit(task4);

        Thread.sleep(5);
        result.cancel(true);

        executorService.shutdown();
*/
       
       /* ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(new Task5(4000));
        executorService.submit(new Task5(3000));
        executorService.submit(new Task5(2000));
        executorService.submit(new Task5(1000));
        executorService.submit(new Task5(1000));
        executorService.submit(new Task5(1000));
        executorService.submit(new Task5(1000));
        executorService.submit(new Task5(1000));
        Thread.sleep(1000l);
        List<Runnable> queudTasks = executorService.shutdownNow();
        System.out.println("Tasks that hasn't started: " + queudTasks.size());
*/

       /* Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Handling the Exception occurred in thread " + t.getName());
            }
        });

        new Task6().start();
        new Task6().start();*/

       /* Task7 task7= new Task7();
        Runnable runnable=()->{
            System.out.println("Runnable Thread Name:"+Thread.currentThread().getName());
            List<String> stList= Arrays.asList("emp1","emp2","emp3");
            task7.printElements(stList);
        };

        CompletableFuture<Void> completableFuture=CompletableFuture.runAsync(runnable);

        completableFuture.get();

        System.out.println("Main Thread Name:"+Thread.currentThread().getName());
*/



       /* Supplier<String> s1=()->{
            System.out.println("Supplier thread "+Thread.currentThread().getName());
            return "Hello";
        };

        CompletableFuture.supplyAsync(s1)
                .thenApply(r->{
                    System.out.println("thenApply thread "+Thread.currentThread().getName());
                    return r+" World";
                });
*/
        /*Task10: Print Hello World using CompletableFuture SupplyAsync and thenAccept*/
        Supplier<Integer> s1=()->{
            System.out.println("Supplier thread "+Thread.currentThread().getName());
            return 10;
        };

        CompletableFuture.supplyAsync(s1)
                .thenApply(r->{
                    System.out.println("thenApply thread "+Thread.currentThread().getName());
                    return r/0;
                }).exceptionally(ex->{
            System.out.println(ex.getMessage());
            return 0; // as it needs to be return an int...returned 0
        });


        System.out.println("Thread main finished");
    }
}

/*
    Task1: Create threads t1 and t2 using class which extends Thread class and execute the task to loop and
    print integers from 1 to 10 */
class Task1 extends Thread{
public void  run(){

    for (int i=1; i<11; i++){
        System.out.println("[ "+currentThread().getName()+" is "+i +" ]"
                +" Priority "+Thread.currentThread().getPriority() );
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
}

/*Task2: Create a runnable task to print integers from 1 to 10 */
class Task2 implements Runnable{
//    @Override
    public void run() {
        for (int i=1; i<11; i++){
            System.out.println("[ "+ Thread.currentThread().getName() +" is "+i +" ]"
                    +" Priority "+Thread.currentThread().getPriority() );
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

/*Task3: Create a task to sum all n numbers using Executor Service, check if task is finished and
if finished print the result */
class Task3 implements Callable<Integer> {
private Integer n;

    public Task3(Integer n) {
        this.n=n;
    }

    @Override
    public Integer call() throws Exception {
        int sum=0;
        for (int i=1; i<=n; i++){
            sum+=i;
            System.out.println("[ "+ Thread.currentThread().getName() +" is "+i +" ]"
                    +" Priority "+Thread.currentThread().getPriority() + " Adding "+ i+ " sum is "+sum);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }
}

/*Task4: Cacel the task while calculating the sum of n numbers using Executor Service?*/
class Task4 implements Callable<Integer>{
    private Integer n;
    public Task4(Integer n) {
        this.n=n;
    }


    @Override
    public Integer call() throws Exception {

            int sum = 0;
            for (int i = 1; i <= n; i++) {
                System.out.println("[" + Thread.currentThread().getName() + "] Adding " + i);
                sum += i;

                if (Thread.interrupted()) {
                    System.out.println("Cancelling the task...");
                    return -1;
                }
            }
            return sum;


    }
}

/*Task5: Terminate all tasks using shutdownNow method using Executor Service?
*
*
 * Terminates all tasks assigned to ExecutorService by using ExecutorService.shutdownNow() method
 * This method terminates immediately the running tasks and retrieves the list of tasks that hasn't
 * started yet
 *
 * As in this example the thread pool has size 5 and 8 tasks where assigned, then it must return 3
 * tasks that were queued
 */
class  Task5 implements Callable<Integer> {
    private Integer n;

    public Task5(Integer n) {
        this.n = n;
    }


    public Integer call() {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            System.out.println("[" + Thread.currentThread().getName() + "] Adding " + i);
            sum += i;
            try {
                Thread.sleep(100l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }
}

/*Task6: Create class which extends Thread, override run method and throw RuntimeException and
use setDefaultUncaughtExceptionHandler to catch the exception*/
class Task6 extends Thread{
    /*
     * Instantiates an UncaughtExceptionHandler to handle exceptions that may rise in
     * all the threads of the application (including those in a Thread Pool)
     *
     * It's also possible to set an UncaughtExceptionHandler directly to an specific thread object.
     *
     * You may also use a combination of both strategies as specific handlers overrides the global one
     */
    @Override
    public void run() {
        throw new RuntimeException();
    }
}

   /*Task7: Create a list of String and print the values in the list using CompletableFuture RunAsync?*/
class Task7 {


       static void printElements(List<String> stList) {
           for(String s:stList){
               System.out.println(s);
           }

       }
   }
   /*Task8: Create a list of Strings("Julie","John","Bob") and check if John is prsent,
   if prsent print "Equal Found" using CompletableFuture SupplyAsync and thenAccept */
class Task8{

   }


