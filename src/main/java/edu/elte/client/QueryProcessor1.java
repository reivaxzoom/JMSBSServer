//package edu.elte.client;
//
//import edu.elte.singleBussiness.StoreOperations;
//import edu.elte.singleBussiness.StoreOperationsImpl;
//import java.io.File;
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.net.URLClassLoader;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//import java.util.Optional;
//import java.util.Properties;
//import java.util.Random;
//import java.util.ResourceBundle;
//import java.util.function.Function;
//import java.util.function.Supplier;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageConsumer;
//import javax.jms.MessageProducer;
//import javax.jms.ObjectMessage;
//import javax.jms.Session;
//import javax.jms.Topic;
//import javax.jms.Destination;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import org.apache.qpid.client.AMQAnyDestination;
////
////
//public class QueryProcessor1 {
////
//    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(QueryProcessor.class.getName());
//    StoreOperations stOps;
////    private static final String JMSselector = "category='shoes' OR category='sport'  ";
////    private static final String topicName = "addidas";
////    private static final String respQueueName = "responseQueue";
////    Supplier<RequestData> incomingMsg = QueryProcessor::receiveMsg;
//////    public static List<RequestData> listRequestProp;
////
////    
////    
////    public static RequestData receiveMsg() {
////         Session session = null;
////        Connection connection = null;
////            
////        try {
////             log.info("connecting qpid ");
////        Properties properties = new Properties();
////        properties.load(QueryProcessor.class.getClassLoader().getResourceAsStream("message.properties"));
////        Context context = new InitialContext(properties);
////
////        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("localConnectionFactory");
////        connection = connectionFactory.createConnection();
////        connection.start();
////
////        session = connection.createSession(true, Session.SESSION_TRANSACTED);
////        Topic requestTopic = session.createTopic("requestTopic");
//////            MessageConsumer subscriber1 = session.createDurableSubscriber(requestTopic, "tesco");
////        MessageConsumer subscriber1 = session.createDurableSubscriber(requestTopic, topicName, JMSselector, true);
////        /*
////         Context marketplaceContext = Declare("category", String.class);
////         MessageConsumer subscriber1 = session.createDurableSubscriber(requestTopic, "addidas",marketplaceContext.create(Equal("category","shoes").OR( Equal("category","sport"))),true);
////             
////         msg.setStringProperty("category","food")
////         marketplaceContext.setStringProperty(msg,"category","food")
////            
////         */
////        log.info("Connected to topic " + topicName);
////        log.info("JMS Selector: " + JMSselector);
////
//////            listRequestProp = new ArrayList<>();
////        RequestData reqData = new RequestData(Optional.empty());
////
////        ObjectMessage message;
////        context.close();
////
////        while ((message = (ObjectMessage) subscriber1.receive(5000)) != null) {
////            ObjectMessage objMes = (ObjectMessage) message;
////            log.info("\n--- Message to be processed -------------\n");
////            log.info(message);
////            log.info(objMes.getPropertyNames());
////
////            reqData.setId(Optional.of(objMes.getStringProperty("id")));
////            reqData.setAddress(objMes.getStringProperty("address"));
////            reqData.setComments(objMes.getStringProperty("comments"));
////            reqData.setPhone(objMes.getStringProperty("phone"));
////            reqData.setExpDelivery(objMes.getStringProperty("expDelivery"));
////            reqData.setPrio(objMes.getStringProperty("prio"));
////            reqData.setCountry(objMes.getStringProperty("country"));
////            reqData.setCategory(objMes.getStringProperty("category"));
////            reqData.setDeliverAddress(objMes.getStringProperty("deliverAddress"));
////
////            log.info("\n---end Message-------------------------\n");
////            session.commit();
////    }
////            System.out.println("Timeout, leaving program");
////            
////        } catch (IOException | NamingException | JMSException e) {
////            e.printStackTrace();
////        }
////            
////           return null;
////}
////    
////    public  void consumeAll() {
////
////        Session session = null;
////        Connection connection = null;
////        try {
////            
////            
////             log.info("connecting qpid ");
////            Properties properties = new Properties();
////            properties.load(this.getClass().getClassLoader().getResourceAsStream("message.properties"));
////            Context context = new InitialContext(properties);
////
////            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("localConnectionFactory");
////            connection = connectionFactory.createConnection();
////            connection.start();
////
////            session = connection.createSession(true, Session.SESSION_TRANSACTED);
////            Topic requestTopic = session.createTopic("requestTopic");
//////            MessageConsumer subscriber1 = session.createDurableSubscriber(requestTopic, "tesco");
////            MessageConsumer subscriber1 = session.createDurableSubscriber(requestTopic, topicName, JMSselector,true);
/////*
////            Context marketplaceContext = Declare("category", String.class);
////            MessageConsumer subscriber1 = session.createDurableSubscriber(requestTopic, "addidas",marketplaceContext.create(Equal("category","shoes").OR( Equal("category","sport"))),true);
////             
////            msg.setStringProperty("category","food")
////            marketplaceContext.setStringProperty(msg,"category","food")
////            
////*/                    
////            log.info("Connected to topic "+topicName);
////            log.info("JMS Selector: "+JMSselector);
////            
//////            listRequestProp = new ArrayList<>();
////            RequestData reqData = new RequestData(Optional.empty());
////            
////            stOps = new StoreOperationsImpl();
////            ObjectMessage message;
////            context.close();
////            long timeout = 3000;
////            int receivedMsg = 0;
////            int minimum=2;
////            int maximum=6;
////            System.out.println("Start waiting");
////            Thread.sleep(10000);
////            while ((message = (ObjectMessage) subscriber1.receive(timeout)) != null) {
////                ObjectMessage objMes = (ObjectMessage) message;
////                log.info("\n--- Message to be processed -------------\n");
////                log.info(message);
//////                log.info(objMes.getPropertyNames());
////                
////                reqData.setId(Optional.of(objMes.getStringProperty("id")));
////                reqData.setAddress(objMes.getStringProperty("address"));
////                reqData.setComments(objMes.getStringProperty("comments"));
////                reqData.setPhone(objMes.getStringProperty("phone"));
////                reqData.setExpDelivery(objMes.getStringProperty("expDelivery"));
////                reqData.setPrio(objMes.getStringProperty("prio"));
////                reqData.setCountry(objMes.getStringProperty("country"));
////                reqData.setCategory(objMes.getStringProperty("category"));
////                reqData.setDeliverAddress(objMes.getStringProperty("deliverAddress"));
////                
//////                listRequestProp.add(reqData);
////                
////                log.info("\n---end Message-------------------------\n");
////            session.commit();
//////            objMes.getStringProperty()
////                ShoppingCart clientCart = (ShoppingCart) objMes.getObject();
////                List<ClientRequest> resultList = stOps.checkAvailableItems(clientCart);
////                
////
////                
//////  setting random time for process
////                int randomDuration = minimum + (int)(Math.random() * maximum); 
////                Thread.sleep(randomDuration*1000);
////                log.info("Message processed");
////                ShoppingCart resultCart = new ShoppingCart();
////                resultCart.addAll(resultList);
////                sendMessages(resultCart);
////                receivedMsg = receivedMsg + 1;
////            }
////            log.info("Sending response done");
////                    
////            session.close();
////            connection.close();
////            log.info("session closed");
////
////        } catch (JMSException ex) {
////            Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
////        } catch (IOException | NamingException | InterruptedException ex) {
////            Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
////        }
////    }  
////    
////    private static Function<String, Thread> consumerFactory(Storage storage, boolean[] isFinished) {
////		return name -> new Thread(() -> {
////			while (!isFinished[0]) {
////				synchronized (storage) {
////					while (!isFinished[0] && !storage.value.isPresent()) {
////						try {
//////                                                    System.out.println("consumer waiting");
////							storage.wait();
////						} catch (Exception e) {
////							e.printStackTrace();
////						}
////					}
////
////					if (storage.value.isPresent()) {
////						System.out.println(name + " " +" size: "+storage.size());
////                                                
////						storage.notifyAll();
////						storage.value = Optional.empty();
////					}
////				}
////			}
////		});
////	}
////    
////    
////    private static Thread producerThread(Storage storage, boolean[] isFinished) {
////		return new Thread(() -> {
////                    
////			for (int i = 0; i < 100; i++) {
////				synchronized (storage) {
////					while (storage.value.isPresent()) {
////						try {
////							storage.wait();
////						} catch (Exception e) {
////							// TODO Auto-generated catch block
////							e.printStackTrace();
////						}
////					}
////
////                                        RequestData rq = new RequestData(Optional.of(String.valueOf(i)));
////					System.out.println("Producer " + rq);
////                                        storage.value= Optional.of(i);
////                                        storage.add(rq);
////					storage.notifyAll();
////				}
////			}
////
////			isFinished[0] = true;
////		});
////	}
////    
////    
////    
//////     public static void main(String[] args) {
//////
////////        QueryProcessor receiver=new QueryProcessor();
////////        receiver.consumeAll();
//////       receiveMsg();
//////        
////////        Storage storage = new Storage();
////////        int id= new Random().nextInt(10);
////////        storage.value = Optional.of(id);
////////		// boolean badFinished = false;
////////		boolean isFinished[] = { false };
////////		final int[] someArray = { 0 };
//////////		someArray[0] = 5323;
////////
////////                Function<String, Thread> makeConsumer = consumerFactory(storage, isFinished);
////////
////////		producerThread(storage, isFinished).start();
////////		for (int i = 0; i < 100; i++) {
////////			makeConsumer.apply("Consumer " + i).start();
//////////                        System.out.println("main:"+i);
////////		}
//////        
//////        
//////        
//////    }
////    
////    
////        
////    public void sendMessages(ShoppingCart cart) {
////        Session session = null;
////        MessageProducer producer = null;
////        Connection connection = null;
////         Context context=null;
////        
////
////        try {
////            log.info("Preparing responses");
////            Properties properties = new Properties();
////            properties.load(getClass().getClassLoader().getResourceAsStream("message.properties"));
////            context = new InitialContext(properties);
////
////            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("localConnectionFactory");
////            connection = connectionFactory.createConnection();
////            connection.start();
////            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//////            Queue queue = (Queue) context.lookup("responseQueue");
//////            Destination queue = new AMQAnyDestination("responseQueue; {create: always}");
////            Destination queue = new AMQAnyDestination(respQueueName);
////            producer = session.createProducer(queue);
////
////            ObjectMessage m = session.createObjectMessage(cart);
////            m.setIntProperty("Id", 100);
////            m.setStringProperty("name", "Addidas");
////            m.setStringProperty("addres", "Sport center utca");
////            m.setDoubleProperty("number", 1);
////            m.setJMSTimestamp(Instant.now().toEpochMilli());
////            producer.send((Message) m);
////            log.info("Request answered:\n " + m);
////            
////
////        } catch (JMSException | NamingException | IOException |URISyntaxException   ex) {
////            Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
////        } finally {
////            try {
////                producer.close();
////                connection.close();
////                session.close();
////                context.close();
////               
////            } catch (JMSException | NamingException ex) {
////                Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
////            }
////        }
////    }
////    
//    public void regenerateDb() {
//        stOps = new StoreOperationsImpl();
//        log.info("removing database");
//        stOps.removeAllListItems();
//        log.info("populating database");
//        stOps.createSampleItems();
//        stOps.findAllItems().stream().forEach(System.out::println);
//    }
////
////   
////    
////    
//////    public static void main(String[] args) {
//////
//////        QueryProcessor receiver=new QueryProcessor();
//////        receiver.consumeAll();
//////    }
////
//    public void displayrConf() {
//      String path="";
//        try {
//            log.info("Displaing configuration");
//        File file = new File("resources");
//        URL[] urls = {file.toURI().toURL()};
//        path=urls[0].getFile();
//        ClassLoader loader = new URLClassLoader(urls);
//        
//        ResourceBundle bundle = ResourceBundle.getBundle("message", Locale.getDefault(), loader);
//        System.out.println("qpid properties");
//        System.out.println("topic.requestTopic="+bundle.getString("topic.requestTopic"));
//        System.out.println("queue.responseQueue="+bundle.getString("queue.responseQueue"));
//        System.out.println("connectionfactory.localConnectionFactory="+bundle.getString("connectionfactory.localConnectionFactory"));
//        System.out.println("jms properties");
//        System.out.println("SPORTSTOPICNAME="+bundle.getString("SPORTSELECTION"));
//        System.out.println("SPORTSTOPICNAME="+bundle.getString("SPORTSTOPICNAME"));
//        
//        } catch (Exception e) {
//            System.out.println(e);
//            System.out.println("looking at: "+path);
//        }
//    }
//    
//}
////
////
////
////
////
////class Storage extends ArrayList<RequestData> {
////	Optional<Integer> value = Optional.empty();
////}
