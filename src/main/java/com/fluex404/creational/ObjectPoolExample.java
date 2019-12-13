package com.fluex404.creational;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ObjectPoolExample {
    private ObjectPool<ExportingProcess> pool;
    private AtomicLong processNo = new AtomicLong(0);
    public void setUp(){
        // Create a pool of objects of type ExportingProcess
        /** Parameters:
         *     1) Minimum number of special ExportingProcess instances residing in the pool = 4
         *     2) Maximum number of special ExportingProcess instances residing in the pool = 10
         *     3) Time in seconds for periodical checking of minObjects / maxObjects conditions
         *        in a separate thread = 5.
         *        -->When the number of ExportingProcess instances is less than minObjects,
         *        missing instances will be created.
         *        -->When the number of ExportingProcess instances is greater than maxObjects,
         *        too many instances will be removed.
         *        -->If the validation interval is negative, no periodical checking of
         *        minObjects / maxObjects conditions in a separate thread take place.
         *        These boundaries are ignored then.
         */
        pool = new ObjectPool<ExportingProcess>(4, 10, 5) {
            @Override
            protected ExportingProcess createObject() {
                // create a test object which takes some time for creating
                return new ExportingProcess(processNo.incrementAndGet());
            }
        };
    }
    public void tearDown(){
        pool.shutdown();
    }
    public void testObjectPool(){
        ExecutorService executor = Executors.newFixedThreadPool(8);

        // execute 8 tasks in separate threads
        executor.execute(new ExportingTask(pool, 1));
        executor.execute(new ExportingTask(pool, 2));
        executor.execute(new ExportingTask(pool, 3));
        executor.execute(new ExportingTask(pool, 4));
        executor.execute(new ExportingTask(pool, 5));
        executor.execute(new ExportingTask(pool, 6));
        executor.execute(new ExportingTask(pool, 7));
        executor.execute(new ExportingTask(pool, 8));

        executor.shutdown();
        try{
            executor.awaitTermination(30, TimeUnit.SECONDS);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ObjectPoolExample o = new ObjectPoolExample();
        o.setUp();
        o.tearDown();
        o.testObjectPool();
    }
}

abstract class ObjectPool<T> {
    /**
     * pool implementation is based on ConcurrentLinkedQueue from the java.util.concurrent package.
     * ConcurrentLinkedQueue is a thread-safe queue base on linked nodes.
     * Because the queue follows FIFO technique(first-in-first-out).
     */
    private ConcurrentLinkedQueue<T> pool;

    /**
     * ScheduledExecuterService start a special task in a separate thread and observes
     * the minium and maximun number of objects in the pool periodically in a specified
     * time(with parameter validationInterval).
     * When the number of object is less thatn the minium, missing intances will be created.
     * When the number of objects is greater that the maximun, too many instances will be removed;
     */
    private ScheduledExecutorService executorService;
    /**
     * Create the pool
     *
     * @Param minObjects: the minium number of objects residing in the pool
     */
    public ObjectPool(final int minObjects) {
        // initialize pool
        initialize(minObjects);
    }
    /**
     * Creates the pool.
     * @param minObjects: minium number of objects residing in the pool.
     * @param maxObjects: maximum number of objects residing in the pool.
     * @param validationInterval: time in secounds for priodical checking of
     * minObjects/maxObjects conditions in separated thread.
     *
     * When the number of objects is less than minObject, missing instances will be created.
     * When the number of objects is greater tahn maxObjects, too many instances will be removed.
     */
    public ObjectPool(final int minObjects, final int maxObjects, final long validationInterval){
        // initialize pool
        initialize(minObjects);
        // check pool conditions in a separate thread
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(() -> {
            int size = pool.size();

            if(size < minObjects) {
                int sizeToBeAdded = minObjects + size;
                for(int i =0;i<sizeToBeAdded;i++) {
                    pool.add(createObject());
                }
            } else if(size > maxObjects) {
                int sizeToBeRemoved = size - maxObjects;
                for(int i=0;i<sizeToBeRemoved;i++) {
                    pool.poll();
                }
            }
        }, validationInterval, validationInterval, TimeUnit.SECONDS);
    }
    /**
     * Gets the next free object from the pool. If the pool doesn't contain any objects
     * a new object will be created and given to the caller of this method back.
     *
     * @return T borrowed object
     */
    public T borrowObject(){
        T object;
        if((object = pool.poll()) == null) {
            object = createObject();
        }
        return object;
    }
    /**
     * Return object back to the pool.
     * @param object object to be returned
     */
    public void returnObject(T object) {
        if(object == null) {
            return;
        }
        this.pool.offer(object);
    }
    /**
     * Shutdown this pool.
     */
    public void shutdown(){
        if(executorService != null) {
            executorService.shutdown();
        }
    }
    /**
     * Creates a new object.
     * @return T new object
     */
    protected abstract T createObject();

    private void initialize(final int minObject) {
        pool = new ConcurrentLinkedQueue<T>();
        for(int i=0;i<minObject;i++) {
            pool.add(createObject());
        }
    }
}
class ExportingProcess{
    private long processNo;

    public ExportingProcess(long processNo){
        this.processNo = processNo;
        // do some expensive calls/tasks here in future
        // .....
        System.out.println("Object with process no. "+processNo+" was created");
    }
    public long getProcessNo(){
        return processNo;
    }
}
class ExportingTask implements Runnable{
    private ObjectPool<ExportingProcess> pool;
    private int threadNo;

    public ExportingTask(ObjectPool<ExportingProcess> pool, int threadNo){
        this.pool = pool;
        this.threadNo = threadNo;
    }
    @Override
    public void run() {
        // get an object from the pool
        ExportingProcess exportingProcess = pool.borrowObject();
        System.out.println("Thread "+threadNo+" : Object with process no."
            +exportingProcess.getProcessNo()+" was borrowed");

        //you can do something here in future
        //..............

        // return ExportingProcess instance back to the pool
        pool.returnObject(exportingProcess);

        System.out.println("Thread "+threadNo+" : Object with process no."
            +exportingProcess.getProcessNo()+" was returned");
    }
}