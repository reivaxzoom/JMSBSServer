package elte.sportStore.view;

import edu.elte.client.QueryProcessor;
import elte.sportStore.model.RequestData;
import edu.elte.client.threads.Processor;
import edu.elte.client.threads.Response;
import edu.elte.client.threads.Suscription;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Xavier
 */
public class Launcher {

     static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Launcher.class.getName());
     
//     public static final String addidasSelector = "rd.category = 'sport' OR rd.category = 'shoes' OR rd.category = 'test'";
     public static final String addidasSelector = "category='shoes' OR category='sport' OR category= 'test'  ";
      public static final String reqTopicName = "requestTopic";
    public static final String topicName = "addidas";
    public static final String respQueueName = "responseQueue";
     
     
//     public static  ShoppingCart resultCart=new ShoppingCart();
    public static BlockingQueue<RequestData> queue;
    
    /**
     * @param args
     */
    public static void main(String[] args) throws IOException  {
        log.info("Starting program");
        ExecutorService executor = Executors.newFixedThreadPool(2); 
        queue= new ArrayBlockingQueue<>(20);
        
//        if (args != null && args.length > 0) {
//            String option = args[0];
//            String[] args2 = new String[0];
//            if (args.length > 1) {
//                args2 = new String[args.length - 1];
//                System.arraycopy(args, 1, args2, 0, args2.length);
//            }
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Enter some text, or '" + "exit" + "' to quit");
      Future fprod = null;
        
       while (true) {
           System.out.print("> ");
         String option = br.readLine();
//         System.out.println(input);
         
            
            switch (option) {
//                case "process":
//                    {
//                        log.info("Processing transaction");
//                        new QueryProcessor().consumeAll();
//                        break;
//                    }
                
                case "reply":{
                       log.info("starting procesor");
                        Response consumer = new Response(queue);
                        Thread consume =new Thread(consumer);
                        consume.setName("consumer");
                        Future fcons=executor.submit(consume);
                        log.info("queue size:" + queue.size());
                        
                        break;
                    }
                
                
                case "suscr":{
                    log.info("Register addidas");
                    new Suscription().addidas();
                    break;
                }
                case "susreply":{
                    log.info("Register addidas");
                    new Suscription().suscribeReplier();
                    break;
                }
                
                
                
                
                case "dboperations":{
                        log.info("processing regenerationDb");
                        new QueryProcessor().regenerateDb();
                        break;
                    }
                case "conf":{
                        log.info("checking configuration");
                        new QueryProcessor().displayrConf();
                        break;
                    }
                case "process":{
                       log.info("starting procesor");
                        Processor processor = new Processor(queue);
                        Thread process =new Thread(processor);
                        process.setName("processor");
                        Future fcons=executor.submit(process);
                        log.info("cart size:" + queue.size()+" is done: "+ fcons.isDone());
                        
                        break;
                    }
//                case "receive":{
////                        log.info("fprod"+ fprod.isDone());
//                        Producer producer = new Producer(queue);
//                        Thread produce =new Thread(producer);
//                        produce.setName("productor");
//                        fprod=executor.submit(produce);
//                       log.info("queue size:" + queue.size());
//                        break;
//                        
//                    }
//                  case "query":{
//                        log.info("starting querie");
//                        Query query = new Query(queue);
//                        Thread qry = new Thread(query);
//                        qry.setName("query");
//                        Future fqry= executor.submit(qry);
//                        log.info("queue size:" + queue.size());
//                        break;
//                    }
                   case "show":{
                        log.info("showing queue");
                        queue.stream().forEach(System.out::println);
                        break;
                    }
//                case "receive":{
//                        log.info("starting receiver");
//                        log.info("fprod"+ fprod.isDone());
//                        Producer producer = new Producer(queue);
//                        Thread produce =new Thread(producer);
//                        produce.setName("productor");
//                        fprod=executor.submit(produce);
//                       log.info("queue size:" + queue.size());
//                        break;
//                        
//                    }
                 case "size":{
                        log.info("showing size");
                        log.info("queue size:" + queue.size());
                        break;
                    }
//                 
//                  case "query":{
//                        log.info("starting querie");
//                        Query query = new Query(queue);
//                        Thread qry = new Thread(query);
//                        qry.setName("query");
//                        Future fqry= executor.submit(qry);
//                        log.info("queue size:" + queue.size());
//                        break;
//                    }
                
                case "exit":{
                    System.out.println("Exiting.");
                    return;
                    
                }
                default:
                {
                    log.warn("no valid option selected");
                    System.out.println("Not valid option");
                }
            }
        }
    }
    
    
    
    
}