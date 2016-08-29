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
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.qpid.client.AMQAnyDestination;
//import static org.springframework.data.mongodb.core.WriteResultChecking.LOG;


public class QueryProcessor {

//    private static final Log LOG = LogFactory.getLog(QueryProcessor.class);
    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(QueryProcessor.class.getName());
    StoreOperations stOps;
    private static final String JMSselector = "category='shoes' OR category='sport'  ";
    private static final String topicName = "addidas";

    public void consumeAll() {

        Session session = null;
        Connection connection = null;
        try {
            log.info("connecting qpid to");
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("message.properties"));
            Context context = new InitialContext(properties);

            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("localConnectionFactory");
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            Topic requestTopic = session.createTopic("requestTopic");
//            MessageConsumer subscriber1 = session.createDurableSubscriber(requestTopic, "tesco");
            MessageConsumer subscriber1 = session.createDurableSubscriber(requestTopic, topicName, JMSselector,true);
/*
            Context marketplaceContext = Declare("category", String.class);
            MessageConsumer subscriber1 = session.createDurableSubscriber(requestTopic, "addidas",marketplaceContext.create(Equal("category","shoes").OR( Equal("category","sport"))),true);
             
            msg.setStringProperty("category","food")
            marketplaceContext.setStringProperty(msg,"category","food")
            
*/                    
            log.info("Connected to topic "+topicName);
            log.info("JMS Selector: "+JMSselector);
            stOps = new StoreOperationsImpl();
            ObjectMessage message;
            context.close();
            long timeout = 3000;
            int receivedMsg = 0;
            int minimum=2;
            int maximum=6;
            while ((message = (ObjectMessage) subscriber1.receive()) != null) {
                ObjectMessage objMes = (ObjectMessage) message;
                log.info("\n--- Message to be processed -------------\n");
                log.info(message);
                log.info("\n---end Message-------------------------\n");
            session.commit();
            
                ShoppingCart clientCart = (ShoppingCart) objMes.getObject();
                List<ClientRequest> resultList = stOps.checkAvailableItems(clientCart);
                
//  setting random time for process
                int randomDuration = minimum + (int)(Math.random() * maximum); 
                Thread.sleep(randomDuration*1000);
                log.info("Message processed");
                ShoppingCart resultCart = new ShoppingCart();
                resultCart.addAll(resultList);
                sendMessages(resultCart);
                receivedMsg = receivedMsg + 1;
            }
            log.info("Sending response done");
                    
            session.close();
            connection.close();
            log.info("session closed");

        } catch (JMSException ex) {
            Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | NamingException | InterruptedException ex) {
            Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    

    public void sendMessages(ShoppingCart cart) {
        Session session = null;
        MessageProducer producer = null;
        Connection connection = null;
         Context context=null;
        

        try {
            log.info("Preparing responses");
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("message.properties"));
            context = new InitialContext(properties);

            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("localConnectionFactory");
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            Queue queue = (Queue) context.lookup("responseQueue");
//            Destination queue = new AMQAnyDestination("responseQueue; {create: always}");
            Destination queue = new AMQAnyDestination("responseQueue");
            producer = session.createProducer(queue);

            ObjectMessage m = session.createObjectMessage(cart);
            m.setIntProperty("Id", 100);
            m.setStringProperty("name", "Addidas");
            m.setStringProperty("addres", "Sport center utca");
            m.setDoubleProperty("number", 1);
            m.setJMSTimestamp(Instant.now().toEpochMilli());
            producer.send((Message) m);
            log.info("Request answered:\n " + m);
            

        } catch (JMSException | NamingException | IOException |URISyntaxException   ex) {
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
        log.info("removing database");
        stOps.removeAllListItems();
        log.info("populating database");
        stOps.createSampleItems();
        stOps.findAllItems().stream().forEach(System.out::println);
    }

//    public static void main(String[] args) {
//
//        QueryProcessor receiver=new QueryProcessor();
//        receiver.consumeAll();
//    }
}
