package oldcode;

import elte.sportStore.model.Item;
import elte.sportStore.model.RequestData;
import edu.elte.client.ShoppingCart;
import edu.elte.singleBussiness.StoreOperations;
import edu.elte.singleBussiness.StoreOperationsImpl;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.qpid.client.AMQAnyDestination;

/**
 *
 */
public class Consumer implements Runnable {

    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Consumer.class.getName());
    private final BlockingQueue<RequestData> queue;
    private StoreOperations stOps;
    private static final String respQueueName = "responseQueue";
    private RequestData incReq;
    private MessageProducer producer = null;

    public Consumer(BlockingQueue<RequestData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        log.info("starting consumer");

//        while (true) {
        if(!queue.isEmpty()){
        log.info("processing: " + queue.size());
            processReq();
        }
        else{log.info("Nothing to process");}
        log.info("queue size:" + queue.size());
    }

    private void processReq() {
        stOps = new StoreOperationsImpl();
        Session session = conectQueue();
        while ((incReq = queue.poll()) != null) {
            ShoppingCart clientCart = (ShoppingCart) incReq.getItems();
            List<Item> resultList = stOps.checkAvailableItems(clientCart);
            if(!resultList.isEmpty()){
                log.info("Message processed");
                ShoppingCart resultCart = new ShoppingCart();
                resultCart.addAll(resultList);
                sendMessages(session, incReq, resultCart);
            }
        }
    }

    public void sendMessages(Session session, RequestData rd, ShoppingCart cart) {
        MessageProducer producer = null;
        log.info("Preparing responses");

        try {
            Destination queue = new AMQAnyDestination(respQueueName);
            producer = session.createProducer(queue);

            if (cart.size() > 0) {
                ObjectMessage m = session.createObjectMessage(cart);
                m.setStringProperty("id", rd.getId());
                m.setIntProperty("requestId", 100);
                m.setStringProperty("name", "Addidas");
                m.setStringProperty("addres", "Sport center utca");
                m.setDoubleProperty("number", 1);
                m.setStringProperty("client", rd.getName());
                m.setStringProperty("address", rd.getAddress());
                m.setStringProperty("country", rd.getCountry());
                m.setStringProperty("phone", rd.getPhone());
                m.setStringProperty("category", rd.getCategory());
                m.setJMSTimestamp(Instant.now().toEpochMilli());
                producer.send((javax.jms.Message) m);
                log.info("Request answered:\n " + m);
            } else {
                log.info("no elements found");
            }
        } catch (JMSException | URISyntaxException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }

    private Session conectQueue() {
        Session session = null;
        Connection connection = null;

        Context context = null;
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("message.properties"));
            context = new InitialContext(properties);

            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("localConnectionFactory");
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
//            Queue queue = (Queue) context.lookup("responseQueue");
//            Destination queue = new AMQAnyDestination("responseQueue; {create: always}");
            Destination queue = new AMQAnyDestination(respQueueName);
            producer = session.createProducer(queue);

            log.info("Connected to queue " + respQueueName);
        } catch (NamingException | JMSException | IOException ex) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                context.close();
            } catch (NamingException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return session;
    }
}
