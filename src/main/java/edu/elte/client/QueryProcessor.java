package edu.elte.client;

import edu.elte.singleBussiness.StoreOperations;
import edu.elte.singleBussiness.StoreOperationsImpl;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.qpid.client.AMQAnyDestination;

public class QueryProcessor {

    private static final Log LOG = LogFactory.getLog(QueryProcessor.class);
    StoreOperations stOps;

//    public void consumeAll() {
//
//        Session session = null;
//        Connection connection = null;
//        MessageConsumer consumer = null;
//        try {
//            Properties properties = new Properties();
//            properties.load(this.getClass().getClassLoader().getResourceAsStream("message.properties"));
//            Context context = new InitialContext(properties);
//
//            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("localConnectionFactory");
//            connection = connectionFactory.createConnection();
//            connection.start();
//
//            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//             AMQDestination topic = new AMQAnyDestination("requestTopic");
////            Queue topic = (Queue) context.lookup("requestTopic");
////            Destination topic = (Queue) context.lookup("requestTopic");
////            AMQTopic topic = (AMQTopic) context.lookup("requestTopic");
//            consumer = session.createConsumer(topic);
//
//            stOps = new StoreOperationsImpl();
//            ObjectMessage message;
//
//            long timeout = -1;
//
//            while ((message = (ObjectMessage) consumer.receive(timeout)) != null) {
//
//                ObjectMessage objMes = (ObjectMessage) message;
//                System.out.println("\n------------- Msg -------------");
//                System.out.println(message);
//                System.out.println("-------------------------------\n");
//
//                ShoppingCart clientCart = (ShoppingCart) objMes.getObject();
//                List<ClientRequest> resultList = stOps.checkAvailableItems(clientCart);
//                Thread.sleep(2000);
//                ShoppingCart resultCart = new ShoppingCart();
//                resultCart.addAll(resultList);
//                sendMessages(resultCart);
//
//            }
//
//        } catch (IOException | NamingException | JMSException|URISyntaxException| NullPointerException| InterruptedException ex) {
//            Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                consumer.close();
//                session.close();
//                connection.close();
//            } catch (JMSException ex) {
//                Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    
    
    
    public void consumeAll() {

        Session session = null;
        Connection connection = null;
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("message.properties"));
            Context context = new InitialContext(properties);

            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("localConnectionFactory");
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            Topic requestTopic = session.createTopic("requestTopic");
//            MessageConsumer subscriber1 = session.createDurableSubscriber(requestTopic, "tesco");"Beverages","Meat","Cleaners","PersonalCare"
            MessageConsumer subscriber1 = session.createDurableSubscriber(requestTopic, "wallmart","category='Cleaners' OR category='Personal Care' OR category='Meat' OR category= 'Beverages'  ",true);
             
            
            

            stOps = new StoreOperationsImpl();
            ObjectMessage message;
            context.close();
            long timeout = -1;
            int receivedMsg = 0;
            int minimum=2;
            int maximum=6;
            while ((message = (ObjectMessage) subscriber1.receive()) != null) {
                ObjectMessage objMes = (ObjectMessage) message;
                System.out.println("\n------------- Msg -------------");
                System.out.println(message);
                System.out.println("-------------------------------\n");
            session.commit();
            
                ShoppingCart clientCart = (ShoppingCart) objMes.getObject();
                List<ClientRequest> resultList = stOps.checkAvailableItems(clientCart);
                
//  setting random time for process
                int randomDuration = minimum + (int)(Math.random() * maximum); 
                Thread.sleep(randomDuration*1000);
    
                ShoppingCart resultCart = new ShoppingCart();
                resultCart.addAll(resultList);
                sendMessages(resultCart);
                receivedMsg = receivedMsg + 1;
            }
            session.close();
            connection.close();

        } catch (JMSException ex) {
            Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | NamingException ex) {
            Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    public void sendMessages(ShoppingCart cart) {
        Session session = null;
        MessageProducer producer = null;
        Connection connection = null;
         Context context=null;
        

        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("message.properties"));
            context = new InitialContext(properties);

            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("localConnectionFactory");
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup("responseQueue");
//            Destination queue = new AMQAnyDestination("responseQueue; {create: always}");
//            Destination queue = new AMQAnyDestination("responseQueue");
            producer = session.createProducer(queue);

            ObjectMessage m = session.createObjectMessage(cart);
            m.setIntProperty("Id", 100);
            m.setStringProperty("name", "wallmart");
            m.setStringProperty("addres", "vaci ut");
            m.setDoubleProperty("number", 9);
            m.setJMSTimestamp(Instant.now().toEpochMilli());
            producer.send((Message) m);
            System.out.println("Sent: " + m);
            

        } catch (JMSException | NamingException | IOException  ex) {
            Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                producer.close();
                connection.close();
                session.close();
                context.close();
               
            } catch (JMSException ex) {
                Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void regenerateDb() {
        stOps = new StoreOperationsImpl();
        LOG.info("removing database");
        stOps.removeAllListItems();
        LOG.info("populating database");
        stOps.createSampleItems();
        stOps.findAllItems().stream().forEach(System.out::println);
    }

//    public static void main(String[] args) {
//
//        QueryProcessor receiver=new QueryProcessor();
//        receiver.consumeAll();
//    }
}
