package oldcode;

import edu.elte.client.QueryProcessor;
import elte.sportStore.model.RequestData;
import edu.elte.client.ShoppingCart;
import edu.elte.singleBussiness.StoreOperations;
import edu.elte.singleBussiness.StoreOperationsImpl;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Producer implements Runnable {

    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Producer.class.getName());

    private final BlockingQueue<RequestData> queue;
    private static final String JMSselector = "category='shoes' OR category='sport'";
    private static final String topicName = "supermarket";
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//    MessageConsumer suscriber1 = conectTopic();

    public Producer(BlockingQueue<RequestData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
//             receive(suscriber1);
        consumeAll();
    }

    public void receive(final MessageConsumer subscriber1) {
        try {
            ObjectMessage message;
            RequestData reqData ;

            while ((message = (ObjectMessage) subscriber1.receive(5000)) != null) {
                ObjectMessage objMes = (ObjectMessage) message;
                log.info("\n--- Message to be processed -------------\n");
                log.info(message);
                reqData = new RequestData();
                reqData.setDate(new Date(objMes.getStringProperty("date")));
                reqData.setId(objMes.getStringProperty("id"));
                reqData.setAddress(objMes.getStringProperty("address"));
                reqData.setComments(objMes.getStringProperty("comments"));
                reqData.setPhone(objMes.getStringProperty("phone"));
                reqData.setExpDelivery(new Date(objMes.getStringProperty("expDelivery")));
                reqData.setPrio(objMes.getStringProperty("prio"));
                reqData.setCountry(objMes.getStringProperty("country"));
                reqData.setCategory(objMes.getStringProperty("category"));
                reqData.setDeliverAddress(objMes.getStringProperty("deliverAddress"));
                reqData.setBudget(Integer.valueOf(objMes.getStringProperty("budget")));
                reqData.setSubOrd(objMes.getStringProperty("subOrd"));
                reqData.setItemNumber(((ShoppingCart) (objMes.getObject())).size());
                reqData.setItems(objMes.getObject());
                log.info("\n---end Message-------------------------\n");
                queue.put(reqData);
            }
        } catch (JMSException |InterruptedException ex) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            log.info("Timeout, leaving receive");
                    
        }

    }

    public static MessageConsumer conectTopic() {
        Session session = null;
        Connection connection = null;
        MessageConsumer subscriber1 = null;
        try {
            log.info("connecting qpid ");
            Properties properties = new Properties();
            properties.load(Producer.class.getClassLoader().getResourceAsStream("message.properties"));
            Context context = new InitialContext(properties);

            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("localConnectionFactory");
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            Topic requestTopic = session.createTopic("requestTopic");
            subscriber1 = session.createDurableSubscriber(requestTopic, topicName, JMSselector, true);
            log.info("Connected to topic " + topicName);
            log.info("JMS Selector: " + JMSselector);
            context.close();
        } catch (NamingException | JMSException  |IOException ex ) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subscriber1;
    }
    
    
    
    
    
        public void consumeAll() {

        Session session = null;
        Connection connection = null;
        try {
            log.info("connecting qpid ");
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("message.properties"));
            Context context = new InitialContext(properties);

            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("localConnectionFactory");
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            Topic requestTopic = session.createTopic(topicName);
//            MessageConsumer subscriber1 = session.createDurableSubscriber(requestTopic, "tesco");
            MessageConsumer subscriber1 = session.createDurableSubscriber(requestTopic, topicName, JMSselector,true);
             
            
            log.info("Connected to topic "+topicName);
            log.info("JMS Selector: "+JMSselector);

            StoreOperations stOps = new StoreOperationsImpl();
            ObjectMessage message;
            context.close();
            long timeout = -1;
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
                clientCart.stream().forEach(System.out::println);
            }
            log.info("Sending response done");
            session.close();
            connection.close();
            log.info("session closed");

        } catch (JMSException ex) {
            Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | NamingException ex) {
            Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
    

