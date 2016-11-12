package oldcode;

import elte.sportStore.model.RequestData;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import elte.sportStore.util.ConcurrentUtils;
 
public class BlockingMain {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
    	ExecutorService executor = Executors.newFixedThreadPool(3); 
        
        // Creating BlockingQueue of size 10
        // BlockingQueue supports operations that wait for the queue to become non-empty when retrieving an element, and
        // wait for space to become available in the queue when storing an element.
        BlockingQueue<RequestData> queue = new ArrayBlockingQueue<>(20);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        Query crunchQuery = new Query(queue);
        
        // starting producer to produce messages in queue
        Thread produce =new Thread(producer);
        // starting consumer to consume messages from queue
        Thread consume =new Thread(consumer);
        Thread query =new Thread(crunchQuery);
        
        
        produce.setName("productor");
        consume.setName("consumer");
        query.setName("Query");
        
        Future fprod=executor.submit(produce);
        Future fcons=executor.submit(consume);
        Future fquery=executor.submit(query);

        if ((fprod == null) || (fprod.isDone()) || (fprod.isCancelled())) {
			// submit a task and return a Future
        	System.out.println("process fprod didnt finish");	
		}

		if ((fcons == null) || (fcons.isDone()) || (fcons.isCancelled())) {
			System.out.println("process fprod didnt finish");
		}
		if ((fquery == null) || (fquery.isDone()) || (fquery.isCancelled())) {
			System.out.println("process fquery didnt finish");
		}

		// if null the task has finished
		if(fprod.get() == null) {
			System.out.println(") Productor terminated successfully");
		} else {
			// if it doesn't finished, cancel it
			fprod.cancel(true);
		}
		if(fcons.get() == null) {
			System.out.println( ") TaskConsume terminated successfully");
		} else {
			fcons.cancel(true);
		}
		
		if(fquery.get() == null) {
			System.out.println( ") fquery terminated successfully");
		} else {
			fquery.cancel(true);
		}

		ConcurrentUtils.stop(executor);
		
        System.out.println("Simulation complete");
    }   
}