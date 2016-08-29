package edu.elte.singleBussiness;

import edu.elte.client.ClientRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import org.javamoney.moneta.Money;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

public class StoreOperationsImpl implements StoreOperations {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(StoreOperationsImpl.class.getName());;
    

    private static final String DBNAME = "tescodb";
    private MongoOperations mongoOps;

    public StoreOperationsImpl() {
        try {
            mongoOps = MongoConnection.getLocalInstance(DBNAME);
        } catch (Exception e) {
        }
    }

//    public StoreOperationsImpl(String dbName) {
//        try {
//            mongoOps = MongoConnection.getLocalInstance(dbName);
//        } catch (Exception e) {
//        }
//    }

    @Override
    public void checkout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addStockOne(ItemStore item) {
        Query q = query(where("name").is(item.getName()));
        mongoOps.updateFirst(q, update("amount", item.getAmount() + 1), ItemStore.class);

    }

    @Override
    public void reduceOne(ItemStore item) {
        if (item.getAmount() > 0) {
            Query q = query(where("name").is(item.getName()));
            mongoOps.updateFirst(q, update("amount", item.getAmount() - 1), ItemStore.class);
        }
        System.out.println("Can not reduce one item");
    }

    @Override
    public void addStock(ItemStore item, int num) {
        Query q = query(where("name").is(item.getName()));
        mongoOps.updateFirst(q, update("amount", item.getAmount() + num), ItemStore.class);
    }

    @Override
    public void reduce(ItemStore item, int num) {
        //TODO Handle with <0 elements
        if (item.getAmount() >= num) {
            Query q = query(where("name").is(item.getName()));
            mongoOps.updateFirst(q, update("amount", item.getAmount() - num), ItemStore.class);
        }
        System.out.println("Can not reduce the requested items");
    }

    @Override
    public void removeItem(ItemStore it) {
        mongoOps.remove(it);
    }

    @Override
    public void insertSampleItems(List<ItemStore> listItem) {
        listItem.stream().forEach(i -> mongoOps.insert(i));
    }

    @Override
    public void removeAllListItems() {
        mongoOps.dropCollection(ItemStore.class);
    }

    @Override
    public ItemStore findItem(String name) {
        Query q = query(where("name").is(name));
        return mongoOps.findOne(q, ItemStore.class);
    }

    @Override
    public void createSampleItems() {
        Locale spain = new Locale("ca", "ES");
        CurrencyUnit eur = Monetary.getCurrency(spain);

        List<ItemStore> listItem = new ArrayList<ItemStore>() {
            {
                add(new ItemStore("Yoghurt", 1, Money.of(9, eur).getNumber()));
                add(new ItemStore("Mango", 2, Money.of(8, eur).getNumber()));
                add(new ItemStore("Pineapple", 8, Money.of(0.69, eur).getNumber()));
                add(new ItemStore("Oranges", 6, Money.of(5.01, eur).getNumber()));
                add(new ItemStore("Apples", 1, Money.of(5.02, eur).getNumber()));
                add(new ItemStore("Bananas", 1, Money.of(5.03, eur).getNumber()));
                add(new ItemStore("Lettuce", 4, Money.of(5.04, eur).getNumber()));
                add(new ItemStore("Tomatoes", 10, Money.of(5.05, eur).getNumber()));
                add(new ItemStore("Squash", 5, Money.of(5.06, eur).getNumber()));
                add(new ItemStore("Celery", 7, Money.of(5.07, eur).getNumber()));
                add(new ItemStore("Cucumber", 7, Money.of(5.08, eur).getNumber()));
                add(new ItemStore("Mushrooms", 7, Money.of(5.09, eur).getNumber()));
                add(new ItemStore("Milk ", 9, Money.of(5.10, eur).getNumber()));
                add(new ItemStore("Cheese", 8, Money.of(5.11, eur).getNumber()));
                add(new ItemStore("Eggs", 8, Money.of(5.12, eur).getNumber()));
                add(new ItemStore("Cottage cheese", 7, Money.of(5.13, eur).getNumber()));
                add(new ItemStore("Sour cream", 1, Money.of(5.14, eur).getNumber()));
                add(new ItemStore("Yogurt", 7, Money.of(5.15, eur).getNumber()));
                add(new ItemStore("Beef", 10, Money.of(5.16, eur).getNumber()));
                add(new ItemStore("Poultry", 9, Money.of(5.17, eur).getNumber()));
                add(new ItemStore("Ham", 10, Money.of(5.18, eur).getNumber()));
                add(new ItemStore("Seafood", 4, Money.of(5.19, eur).getNumber()));
                add(new ItemStore("Lunch meat", 6, Money.of(5.20, eur).getNumber()));
                add(new ItemStore("Soda", 3, Money.of(5.21, eur).getNumber()));
                add(new ItemStore("Juice", 1, Money.of(5.22, eur).getNumber()));
                add(new ItemStore("Coffee", 4, Money.of(5.23, eur).getNumber()));
                add(new ItemStore("Tea", 4, Money.of(5.24, eur).getNumber()));
                add(new ItemStore("Water", 6, Money.of(5.25, eur).getNumber()));
                add(new ItemStore("Beer", 6, Money.of(4.00, eur).getNumber()));
                add(new ItemStore("Noodles", 3, Money.of(5.26, eur).getNumber()));
                add(new ItemStore("Rice", 20, Money.of(5.27, eur).getNumber()));
                add(new ItemStore("Canned", 2, Money.of(5.28, eur).getNumber()));
            }
        };

        LOG.debug("Inserting elements on list: ");
        insertSampleItems(listItem);
    }

    @Override
    public List<ItemStore> findAllItems() {
        return mongoOps.findAll(ItemStore.class);
    }

    @Override
    public ClientRequest checkAvailableItem(ClientRequest cliReq) {
        ItemStore it = findItem(cliReq.getName());
        //TODO add bussines method to limit the quantity offered
//         Optional.of(it).get().getAmount();
        int offAmount;
        ClientRequest reqIt = new ClientRequest();
        if (it != null) {
            if (cliReq.getAmount() <= it.getAmount()) {
                offAmount = cliReq.getAmount();
            } else {
                offAmount = it.getAmount();
            }
            reqIt.setAmount(offAmount);
            reqIt.setName(it.getName());
        }
        return reqIt;

    }

    @Override
    public List<ClientRequest> checkAvailableItems(List<ClientRequest> cliReq) {
        List<ClientRequest> result = cliReq.stream()
                .map(it ->  checkAvailableItem(it)).filter(it1 -> it1.getName()!=null)
                .collect(Collectors.toList());
        return result;
    }

}

//
// @Override
//    public void findItem(String nameItem) {
//         QStore qStore = new QStore("Busca");
//        Iterable<Store> result =storeRep.findAll(qStore.name.eq(STORENAME));
//        System.out.println("Print Stores name "+STORENAME);
//        result.forEach((st) -> {
//            System.out.println("Store: "+st.getName()+ " item :"+ st.getItemStore().stream().filter(p -> p.getName().equals(nameItem)).collect(Collectors.toList()));
//        });
//    }
